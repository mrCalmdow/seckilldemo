package com.flchen.seckilldemo.seckilldemo.config;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import com.flchen.seckilldemo.seckilldemo.entity.OrderDO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author feilongchen
 * @since 2018-09-25 6:25 PM
 */
@Configuration
public class RedisCustomizedConfig {

	@Bean
	public JedisConnectionFactory jedisConnectionFactory() {
		return new JedisConnectionFactory();
	}

	@Bean
	public RedisTemplate<String, OrderDO> redisTemplate4Order(JedisConnectionFactory jedisConnectionFactory) {
		RedisTemplate<String, OrderDO> template = new RedisTemplate<>();
		template.setConnectionFactory(jedisConnectionFactory);
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(new FastJsonRedisSerializer(OrderDO.class));
		return template;
	}
}
