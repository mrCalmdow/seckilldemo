package com.flchen.seckilldemo.seckilldemo.service.impl;

import com.flchen.seckilldemo.seckilldemo.service.AsyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author feilongchen
 * @since 2018-09-20 5:15 PM
 */
@Slf4j
@Service
public class AsyncServiceImpl implements AsyncService {

	private static Lock lock = new ReentrantLock(true);

	@Async
	@Override
	public void doTask() throws InterruptedException {
		if(!lock.tryLock()) {
			return ;
		}
//		Thread.sleep(16000);
		taskExecute();
		log.info("Async task executed.");
		lock.unlock();
	}

	@Async
	@Override
	public void doTask1() throws InterruptedException {
		Thread.sleep(20000);
		log.info("Async task executed1111111.");
	}

	@Override
	public void redisLock() {

	}

	public void taskExecute() {
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
