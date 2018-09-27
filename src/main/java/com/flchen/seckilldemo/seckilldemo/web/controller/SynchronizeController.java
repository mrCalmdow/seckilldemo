package com.flchen.seckilldemo.seckilldemo.web.controller;

import com.flchen.seckilldemo.seckilldemo.service.SynchronizeService;
import com.flchen.seckilldemo.seckilldemo.web.mo.ResponseMO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author feilongchen
 * @since 2018-09-26 3:40 PM
 */
@Slf4j
@RestController
@RequestMapping("/sync")
public class SynchronizeController extends BaseController {

	@Autowired
	private SynchronizeService synchronizeService;

	@GetMapping
	public ResponseMO testNormalSynchronize() {

		if(!synchronizeService.updateIndexes()){
			return error("已经有人在同步了");
		}
		return success();
	}

	@GetMapping("/async")
	public ResponseMO testNormalSynchronize1() {

		synchronizeService.updateIndexes1();

		return success();
	}
}
