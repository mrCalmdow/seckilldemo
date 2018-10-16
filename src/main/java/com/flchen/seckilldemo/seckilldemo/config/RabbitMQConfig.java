package com.flchen.seckilldemo.seckilldemo.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author feilongchen
 * @since 2018-09-28 11:26 AM
 */
@Configuration
public class RabbitMQConfig {

	@Bean
	public Queue helloQueue() {
		return new Queue("hello");
	}

	@Bean
	public Queue orderMessage() {
		return new Queue("order_queue");
	}
}
