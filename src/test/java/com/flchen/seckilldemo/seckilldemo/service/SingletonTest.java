package com.flchen.seckilldemo.seckilldemo.service;

import javafx.application.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author feilongchen
 * @since 2018-10-09 3:22 PM
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SingletonTest {

	@Autowired
	ApplicationContext ctx;

	@Autowired
	SingletonService singletonService;

	@Test
	public void testSingleton() {
		singletonService.initList();
		singletonService.reloadList();
	}
}
