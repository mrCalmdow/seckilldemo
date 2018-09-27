package com.flchen.seckilldemo.seckilldemo.service.impl;

import com.flchen.seckilldemo.seckilldemo.service.RedisLockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 利用redis（单机）实现分布式服务的，多线程排他锁
 * @author feilongchen
 * @since 2018-09-26 6:46 PM
 */
@Slf4j
@Service
public class RedisLockServiceImpl implements RedisLockService {

	private static final String lockKey = "lockKey";
	private static final Integer expireTime = 6000; // 15s
	private static boolean isLock = false;

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Override
	public boolean tryLock() {
		return lock();
	}

	@Override
	public void unlock() {

		// 释放锁：方法一、设置超时时间为0，方法二、删除key
		Long currentTime = System.currentTimeMillis();
		Long oldTime = Long.parseLong(stringRedisTemplate.boundValueOps(lockKey).get());

		//超时释放
		if (currentTime > oldTime) {
			isLock = false;
			log.info("[ UNLOCK ] -- {}  timeout and unlock", System.currentTimeMillis());
			return;
		}

		// 删除key
		stringRedisTemplate.boundValueOps(lockKey).getOperations().delete(lockKey);
		isLock = false;
		log.info("[ UNLOCK ] -- {}  delete lockKey to unlock", System.currentTimeMillis());
	}

	@Override
	public boolean lock() {

		Long currentTime = System.currentTimeMillis();
		Long value = currentTime + expireTime;
//		stringRedisTemplate.boundValueOps(lockKey).set(currentTime.toString(), expireTime, TimeUnit.MILLISECONDS);
		// 如果是首次取锁便成功
		isLock = stringRedisTemplate.boundValueOps(lockKey).setIfAbsent(value.toString());
		if (isLock) {
			log.warn("[ LOCK ] -- {}  ~~~~~idle to get lock", System.currentTimeMillis());
			return isLock;
		}

		// 未获取锁
		// 获取上次锁定设置的超时时间
		String oldValue = stringRedisTemplate.boundValueOps(lockKey).get();
		Long lastTime = Long.parseLong(oldValue);
		if (currentTime > lastTime && null != lastTime) {
			// 超时处理
			stringRedisTemplate.boundValueOps(lockKey).getAndSet(value.toString());
			isLock = true;
			log.warn("[ LOCK ] -- {}  ~~~~~expire and get lock", System.currentTimeMillis());
		}

		// 否则还不能获取锁
		return isLock;
	}

	@Async
	@Override
	public void testLockAsync() {
		log.info("[ METHOD ] -- {}  [task start] into async method ~~~~", System.currentTimeMillis());
		if (!tryLock()) {
			log.info("[ METHOD ] -- {}  [not have lock] out async method ~~~~, so out ~~~~", System.currentTimeMillis());
			return;
		}
		try {
			task();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			unlock();
		}
		log.info("[ METHOD ] -- {}  [task done] out async method ~~~~", System.currentTimeMillis());
	}

	/**
	 * 模拟任务
	 */
	public void task() {
		try {
			log.error("[ TASK ] -- {}  start execute task~~~~~~~~", System.currentTimeMillis());
			Thread.sleep(5000);
			log.error("[ TASK ] -- {}  done execute task~~~~~~~~", System.currentTimeMillis());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
