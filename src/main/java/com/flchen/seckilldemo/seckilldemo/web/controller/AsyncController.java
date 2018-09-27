package com.flchen.seckilldemo.seckilldemo.web.controller;

import com.flchen.seckilldemo.seckilldemo.service.AsyncService;
import com.flchen.seckilldemo.seckilldemo.web.mo.ResponseMO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author feilongchen
 * @since 2018-09-20 5:12 PM
 */
@EnableAsync
@Slf4j
@RestController
@RequestMapping("/async")
public class AsyncController extends BaseController {

	@Autowired
	private AsyncService asyncService;

	@GetMapping
	public ResponseMO test() throws InterruptedException {
		asyncService.doTask();
		return success();
	}

	@GetMapping("/lock")
	public ResponseMO test1() throws InterruptedException {
		asyncService.doTask1();
		return success();
	}
}
