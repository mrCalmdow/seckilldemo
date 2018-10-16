package com.flchen.seckilldemo.seckilldemo.service.rabbit;

import com.flchen.seckilldemo.seckilldemo.entity.mo.OrderMessageMO;
import com.flchen.seckilldemo.seckilldemo.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author feilongchen
 * @since 2018-09-28 12:37 PM
 */
@Slf4j
@Component
@RabbitListener(queues = "order_queue")
public class OrderRabbitReceiver {

	@Autowired
	private OrderService orderService;


	@RabbitHandler
	public void receiveOrderMessage(OrderMessageMO orderMessage) {
		log.info("Receive a order message: " + orderMessage.toString());
		orderService.insertOrderRecord(orderMessage);
	}
}
