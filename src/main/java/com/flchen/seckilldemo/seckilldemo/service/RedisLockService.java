package com.flchen.seckilldemo.seckilldemo.service;

/**
 * @author feilongchen
 * @since 2018-09-26 6:38 PM
 */
public interface RedisLockService {

	boolean tryLock();

	void unlock();

	boolean lock();

	void testLockAsync();
}
