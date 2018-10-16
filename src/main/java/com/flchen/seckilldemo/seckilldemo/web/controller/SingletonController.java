package com.flchen.seckilldemo.seckilldemo.web.controller;

import com.flchen.seckilldemo.seckilldemo.service.SingletonService;
import com.flchen.seckilldemo.seckilldemo.web.mo.ResponseMO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author feilongchen
 * @since 2018-10-09 3:32 PM
 */
@RestController
@RequestMapping("/singleton")
public class SingletonController extends BaseController {

	@Autowired
	private SingletonService singletonService;

	@GetMapping("/testSingleton")
	public ResponseMO testSingleton() {
		singletonService.reloadList();
		return success(singletonService);
	}

	@GetMapping("/size")
	public ResponseMO testSingletonSize() {

		return success(singletonService.getListSize());
	}
}
