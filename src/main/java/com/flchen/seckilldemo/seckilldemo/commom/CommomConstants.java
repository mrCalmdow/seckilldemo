package com.flchen.seckilldemo.seckilldemo.commom;

/**
 * @author feilongchen
 * @since 2018-09-20 10:01 AM
 */
public interface CommomConstants {

	int RESPONSE_CODE_SUCCESS = 0;
	int RESPONSE_CODE_ERROR = 1;

	int MININUM_STOCK = 1;

	String REDIS_STOCK_SUFFIX = "_stock";

	String REDIS_BOUGHT_CACHE_SUFFIX = "_bought_set";

	String REDIS_ORDER_CACHE_SUFFIX = "_order_set";
}
