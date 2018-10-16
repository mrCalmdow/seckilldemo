package com.flchen.seckilldemo.seckilldemo.service.impl;

import com.flchen.seckilldemo.seckilldemo.entity.ProductDO;
import com.flchen.seckilldemo.seckilldemo.repository.ProductAutoRepository;
import com.flchen.seckilldemo.seckilldemo.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author feilongchen
 * @since 2018-10-05 1:45 PM
 */
@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

	private final List<ProductDO> cache = new Vector<>();

	private final Map<String, ProductDO> cacheMap = new ConcurrentHashMap<>();

	@Autowired
	private ProductAutoRepository productAutoRepository;

	@Override
	public List<ProductDO> getProductsFromCache() {
		List<ProductDO> productDOS = new ArrayList<>();
		if (!CollectionUtils.isEmpty(cacheMap)) {
			log.info("From cache: --- getProducts");
			List<ProductDO> list = new ArrayList<>();
			cacheMap.entrySet().forEach(entry -> list.add(entry.getValue()));
			return list;
		}
		log.info("From DB: --- getProducts");
		productDOS = productAutoRepository.findAll();
		if (!CollectionUtils.isEmpty(productDOS)) {
			productDOS.forEach(e -> cacheMap.put(e.getProductName(), e));
		}
		return productDOS;
	}

	@Override
	public ProductDO getProductFromCache(String name) {

		if (!CollectionUtils.isEmpty(cacheMap)) {
			log.info("From cache: --- getProduct");
			ProductDO product = cacheMap.get(name);
			if (null != product) {
				return product;
			}
		}
		log.info("From DB: --- getProduct");
		ProductDO product = productAutoRepository.findByProductName(name);
		updateProductsCache(product);
		return product;
	}

	@Override
	public void updateProductsCache(ProductDO product) {

		log.info("update cache: --- getProduct");
		cacheMap.put(product.getProductName(), product);
	}

	@Override
	public void evictProductsCache() {
		log.info("evict cache: --- getProduct");
		cacheMap.clear();
	}
}
