package com.flchen.seckilldemo.seckilldemo.service.rabbit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author feilongchen
 * @since 2018-09-28 11:31 AM
 */
@Slf4j
@Component
public class Sender {

	@Autowired
	private AmqpTemplate amqpTemplate;

	public void send(String message) {
		amqpTemplate.convertAndSend("hello", message);
		log.info("send a message: " + message);
	}
}
