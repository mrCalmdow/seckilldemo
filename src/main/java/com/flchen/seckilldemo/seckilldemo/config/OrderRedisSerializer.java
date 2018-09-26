package com.flchen.seckilldemo.seckilldemo.config;

import com.flchen.seckilldemo.seckilldemo.entity.OrderDO;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.util.Assert;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author feilongchen
 * @since 2018-09-26 2:35 PM
 */
public class OrderRedisSerializer implements RedisSerializer<OrderDO> {

	private final Charset charset;

	public OrderRedisSerializer() {
		this(StandardCharsets.UTF_8);
	}

	public OrderRedisSerializer(Charset charset) {
		Assert.notNull(charset, "Charset must not be null!");
		this.charset = charset;
	}

	@Override
	public byte[] serialize(OrderDO orderDO) throws SerializationException {
		if(null == orderDO) {
			return new byte[0];
		}

		return new byte[0];
	}

	@Override
	public OrderDO deserialize(byte[] bytes) throws SerializationException {
		return null;
	}
}
