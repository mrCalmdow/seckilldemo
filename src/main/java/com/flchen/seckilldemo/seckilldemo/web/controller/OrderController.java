package com.flchen.seckilldemo.seckilldemo.web.controller;

import com.flchen.seckilldemo.seckilldemo.commom.CommomConstants;
import com.flchen.seckilldemo.seckilldemo.entity.OrderDO;
import com.flchen.seckilldemo.seckilldemo.entity.ProductDO;
import com.flchen.seckilldemo.seckilldemo.entity.UserDO;
import com.flchen.seckilldemo.seckilldemo.repository.OrderAutoRepository;
import com.flchen.seckilldemo.seckilldemo.repository.ProductAutoRepository;
import com.flchen.seckilldemo.seckilldemo.repository.UserAutoRepository;
import com.flchen.seckilldemo.seckilldemo.service.OrderService;
import com.flchen.seckilldemo.seckilldemo.web.mo.OrderingMO;
import com.flchen.seckilldemo.seckilldemo.web.mo.ResponseMO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author feilongchen
 * @since 2018-09-20 1:47 PM
 */
@Slf4j
@RestController
@RequestMapping("/orders")
public class OrderController extends BaseController {

	@Autowired
	private OrderAutoRepository orderAutoRepository;

	@Autowired
	private UserAutoRepository userAutoRepository;

	@Autowired
	private ProductAutoRepository productAutoRepository;

	@Autowired
	private OrderService orderService;

	@PostMapping("/normal")
	public ResponseMO ordering(@RequestBody @Valid OrderingMO orderingMO) {

		UserDO user = userAutoRepository.findByPhone(orderingMO.getPhone());
		ProductDO product = productAutoRepository.findByProductName(orderingMO.getProductName());
		if (null == user || null == product) {
			return error("参数错误，下单失败");
		}
		//1 判断当前产品库存
		if (product.getStock() < CommomConstants.MININUM_STOCK) {
			return error("库存不足，下单失败");
		}
		//2 判断当前是否已经购买
		if (null != orderAutoRepository.findByUser_Id(user.getId())) {
			return error("重复购买，下单失败");
		}
		OrderDO order = new OrderDO();
		order.setUser(user);
		order.setProduct(product);
		order.setMessage("ordering success");
		order.setState(1);
		orderAutoRepository.save(order);
		return success();
	}

	@PostMapping("/redis")
	public ResponseMO orderingByRedis(@RequestBody @Valid OrderingMO orderingMO) {
		UserDO user = userAutoRepository.findByPhone(orderingMO.getPhone());
		ProductDO product = productAutoRepository.findByProductName(orderingMO.getProductName());
		if (null == user || null == product) {
			return error("参数错误，下单失败");
		}
		if (orderService.redisOrdering(product, user.getId())) {
			return success();
		}
		return error("秒杀失败");
	}
}
