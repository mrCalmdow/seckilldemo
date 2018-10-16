package com.flchen.seckilldemo.seckilldemo.service;

import com.flchen.seckilldemo.seckilldemo.service.rabbit.Receiver;
import com.flchen.seckilldemo.seckilldemo.service.rabbit.Sender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author feilongchen
 * @since 2018-09-28 11:41 AM
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitMQTest {

	@Autowired
	private Sender sender;

	@Autowired
	private Receiver receiver;

	@Test
	public void testSendMessage() {
		sender.send("hello, rabbit!");
	}
}
