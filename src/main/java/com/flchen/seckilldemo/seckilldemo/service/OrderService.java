package com.flchen.seckilldemo.seckilldemo.service;

import com.flchen.seckilldemo.seckilldemo.entity.ProductDO;

/**
 * @author feilongchen
 * @since 2018-09-20 1:47 PM
 */
public interface OrderService {

	boolean normalOrdering(ProductDO product);

	boolean redisOrdering(ProductDO product, String userId);

	void initStock();
}
