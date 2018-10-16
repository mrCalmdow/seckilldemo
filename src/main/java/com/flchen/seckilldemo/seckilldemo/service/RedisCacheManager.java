package com.flchen.seckilldemo.seckilldemo.service;

import java.util.List;
import java.util.Map;

/**
 * @author feilongchen
 * @since 2018-10-05 4:17 PM
 */
public interface RedisCacheManager<T> {

	Map<String, T> getCache();

	List<T> getValues();

	T getValue(String key);

	void addOrUpdateValue(String key, T value);

	void evictValue(String key);

	void clearCache();
}
