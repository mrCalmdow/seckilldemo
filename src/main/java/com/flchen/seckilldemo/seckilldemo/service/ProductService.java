package com.flchen.seckilldemo.seckilldemo.service;

import com.flchen.seckilldemo.seckilldemo.entity.ProductDO;

import java.util.List;

/**
 * @author feilongchen
 * @since 2018-10-05 1:45 PM
 */
public interface ProductService {

	List<ProductDO> getProductsFromCache();

	ProductDO getProductFromCache(String name);

	void updateProductsCache(ProductDO product);

	void evictProductsCache();
}
