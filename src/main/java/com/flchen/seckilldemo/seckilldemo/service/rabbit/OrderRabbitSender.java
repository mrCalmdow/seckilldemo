package com.flchen.seckilldemo.seckilldemo.service.rabbit;

import com.flchen.seckilldemo.seckilldemo.entity.mo.OrderMessageMO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author feilongchen
 * @since 2018-09-28 12:40 PM
 */
@Slf4j
@Component
public class OrderRabbitSender {

	@Autowired
	private AmqpTemplate amqpTemplate;

	public void sendOrderMessage(OrderMessageMO orderMessage) {

		amqpTemplate.convertAndSend(orderMessage);
		log.info("Send a order message: " + orderMessage.toString());
	}
}
