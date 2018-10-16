package com.flchen.seckilldemo.seckilldemo.service.impl;

import com.flchen.seckilldemo.seckilldemo.service.RedisCacheManager;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author feilongchen
 * @since 2018-10-05 4:33 PM
 */
@Service
public class RedisCacheManagerImpl<T> implements RedisCacheManager<T> {

	private final Map<String, T> cacheManager = new ConcurrentHashMap<>();

	@Override
	public Map<String, T> getCache() {
		return cacheManager;
	}

	@Override
	public List<T> getValues() {
		if(!CollectionUtils.isEmpty(cacheManager)) {
			List<T> list = new ArrayList<>();
			cacheManager.entrySet().forEach(e -> list.add(e.getValue()));
			return list;
		}
		return null;
	}

	@Override
	public T getValue(String key) {
		if (cacheManager.containsKey(key)) {
			return cacheManager.get(key);
		}
		return null;
	}

	@Override
	public void addOrUpdateValue(String key, T value) {

		cacheManager.put(key, value);
	}

	@Override
	public void evictValue(String key) {
		if (cacheManager.containsKey(key)) {
			cacheManager.remove(key);
		}
	}

	@Override
	public void clearCache() {
		cacheManager.clear();
	}
}
