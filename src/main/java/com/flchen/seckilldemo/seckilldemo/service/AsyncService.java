package com.flchen.seckilldemo.seckilldemo.service;

/**
 * @author feilongchen
 * @since 2018-09-20 5:14 PM
 */
public interface AsyncService {

	/**
	 * 防止并发的异步方法（保证同时只有一个异步方法在执行）
	 * @throws InterruptedException
	 */
	void doTask() throws InterruptedException;

	/**
	 * 防止并发的异步方法（保证同时只有一个异步方法在执行）
	 * @throws InterruptedException
	 */
	void doTask1() throws InterruptedException;

	/**
	 * 使用redis锁定异步方法的执行次数
	 */
	void redisLock();

	void taskExecute();
}
