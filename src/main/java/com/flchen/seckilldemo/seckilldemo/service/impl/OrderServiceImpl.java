package com.flchen.seckilldemo.seckilldemo.service.impl;

import com.flchen.seckilldemo.seckilldemo.commom.CommomConstants;
import com.flchen.seckilldemo.seckilldemo.entity.OrderDO;
import com.flchen.seckilldemo.seckilldemo.entity.ProductDO;
import com.flchen.seckilldemo.seckilldemo.entity.mo.OrderMessageMO;
import com.flchen.seckilldemo.seckilldemo.repository.OrderAutoRepository;
import com.flchen.seckilldemo.seckilldemo.repository.ProductAutoRepository;
import com.flchen.seckilldemo.seckilldemo.repository.UserAutoRepository;
import com.flchen.seckilldemo.seckilldemo.service.OrderService;
import com.flchen.seckilldemo.seckilldemo.service.rabbit.OrderRabbitSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author feilongchen
 * @since 2018-09-25 5:35 PM
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Autowired
	private ProductAutoRepository productAutoRepository;

	@Autowired
	private UserAutoRepository userAutoRepository;

	@Autowired
	private OrderAutoRepository orderAutoRepository;

	@Autowired
	private RedisTemplate<String, OrderDO> redisTemplate4Order;

	@Autowired
	private OrderRabbitSender orderRabbitSender;

	@Override
	public boolean normalOrdering(ProductDO product) {
		return true;
	}

	@Override
	public boolean redisOrdering(ProductDO product, String userId) {
		int stock = Integer.parseInt(stringRedisTemplate.boundValueOps(product.getProductName() + CommomConstants.REDIS_STOCK_SUFFIX).get());
		if(stock < CommomConstants.MININUM_STOCK) {
			log.error("stock not enough.");
			return false;
		}
		if(stringRedisTemplate.boundSetOps(product.getProductName() + CommomConstants.REDIS_BOUGHT_CACHE_SUFFIX).isMember(userId)) {
			log.info("{} has already bought.", userId);
			return false;
		}
		// 减少库存、增加购买记录、生成订单记录
		stringRedisTemplate.boundValueOps(product.getProductName() + CommomConstants.REDIS_STOCK_SUFFIX).increment(-1);
		stringRedisTemplate.boundSetOps(product.getProductName() + CommomConstants.REDIS_BOUGHT_CACHE_SUFFIX).add(userId);

		OrderDO order = new OrderDO();
		order.setProduct(product);
		order.setMessage("success");
		order.setState(1);
		if(1 != redisTemplate4Order.boundSetOps(product.getProductName() + CommomConstants.REDIS_ORDER_CACHE_SUFFIX).add(order)) {
			//添加失败手动回滚
			stringRedisTemplate.boundValueOps(product.getProductName() + CommomConstants.REDIS_STOCK_SUFFIX).increment(1);
			stringRedisTemplate.boundSetOps(product.getProductName() + CommomConstants.REDIS_BOUGHT_CACHE_SUFFIX).remove(userId);
		}
		// TODO generate order record
		OrderMessageMO orderMessageMO = new OrderMessageMO();
		orderMessageMO.setNumber(1);
		orderMessageMO.setProductId(product.getId());
		orderMessageMO.setUserId(userId);
		orderRabbitSender.sendOrderMessage(orderMessageMO);
		return true;
	}

	@PostConstruct
	@Override
	public void initStock() {
		List<ProductDO> productDOS = productAutoRepository.findAll();
		if(!CollectionUtils.isEmpty(productDOS)) {
			productDOS.forEach(productDO -> {
				stringRedisTemplate.boundValueOps(productDO.getProductName() + "_stock").set(productDO.getStock().toString());
			});
		}
	}

	@Override
	public void insertOrderRecord(OrderMessageMO orderMessage) {
		Assert.notNull(orderMessage, "订单信息不能为空");
		OrderDO order = new OrderDO();
		order.setProduct(productAutoRepository.findById(orderMessage.getProductId()).get());
		order.setUser(userAutoRepository.findById(orderMessage.getUserId()).get());
		order.setState(1);
		orderAutoRepository.save(order);
	}
}
