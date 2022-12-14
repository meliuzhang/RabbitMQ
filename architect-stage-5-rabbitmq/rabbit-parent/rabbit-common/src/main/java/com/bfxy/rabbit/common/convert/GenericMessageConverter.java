package com.bfxy.rabbit.common.convert;

import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.MessageConverter;

import com.bfxy.rabbit.common.serializer.Serializer;
import com.google.common.base.Preconditions;

/**
 * 	$GenericMessageConverter
 * @author Alienware
 *
 */
public class GenericMessageConverter implements MessageConverter {

	private Serializer serializer;

	public GenericMessageConverter(Serializer serializer) {
		Preconditions.checkNotNull(serializer);
		this.serializer = serializer;
	}

	/**
	 * 把spring amqp 的message转成自己定义的message类
	 * @param message
	 * @return
	 * @throws MessageConversionException
	 */
	@Override
	public Object fromMessage(org.springframework.amqp.core.Message message) throws MessageConversionException {
		return this.serializer.deserialize(message.getBody());
	}

	/**
	 * 把我们自己定义的message类转成spring amqp的message
	 * @param object
	 * @param messageProperties
	 * @return
	 * @throws MessageConversionException
	 */
	@Override
	public org.springframework.amqp.core.Message toMessage(Object object, MessageProperties messageProperties)
			throws MessageConversionException {
		return new org.springframework.amqp.core.Message(this.serializer.serializeRaw(object), messageProperties);
	}

}
