package com.flchen.seckilldemo.seckilldemo.web.controller;

import com.flchen.seckilldemo.seckilldemo.service.RedisLockService;
import com.flchen.seckilldemo.seckilldemo.web.mo.ResponseMO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author feilongchen
 * @since 2018-09-26 6:15 PM
 */
@RestController
@RequestMapping("/redisLock")
public class RedisLockAsyncController extends BaseController {

	@Autowired
	private RedisLockService redisLockService;

	@GetMapping
	public ResponseMO test() {
		redisLockService.testLockAsync();
		return success();
	}
}
