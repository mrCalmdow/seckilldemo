package com.flchen.seckilldemo.seckilldemo.service.rabbit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author feilongchen
 * @since 2018-09-28 11:37 AM
 */
@Slf4j
@Component
@RabbitListener(queues = {"hello"})
public class Receiver {

	@RabbitHandler
	public void process(String message) {
		log.info("receive message: " + message);
	}

}
