package com.flchen.seckilldemo.seckilldemo.service.impl;

import com.flchen.seckilldemo.seckilldemo.service.AsyncService;
import com.flchen.seckilldemo.seckilldemo.service.SynchronizeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author feilongchen
 * @since 2018-09-26 3:38 PM
 */
@Slf4j
@Service
public class SynchroizeServiceImpl implements SynchronizeService {

	private static Lock lock = new ReentrantLock(true);

	@Autowired
	private AsyncService asyncService;

	@Override
	public boolean updateIndexes() {
		if(!lock.tryLock()) {
			return false;
		}
		try {
			Thread.sleep(5000);
			log.error("updating~~~~~~~");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
		log.error("update completed.~~~~~~~~~~~~~~~");
		return true;
	}

	@Async
	@Override
	public void updateIndexes1() {
		if(!lock.tryLock()) {
			return;
		}
		try {
			log.error("updating~~~~~~~");
			asyncService.taskExecute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
		log.error("update completed.~~~~~~~~~~~~~~~");
	}

}
