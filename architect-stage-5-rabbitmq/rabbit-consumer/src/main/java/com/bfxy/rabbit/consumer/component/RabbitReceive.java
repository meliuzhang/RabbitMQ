package com.bfxy.rabbit.consumer.component;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;

@Component
public class RabbitReceive {

	/**
	 * 	组合使用监听
	 * 	@RabbitListener @QueueBinding @Queue @Exchange
	 * @param message
	 * @param channel
	 * @throws Exception
	 */
	@RabbitListener(bindings = @QueueBinding(
					value = @Queue(value = "queue-1", durable = "true"),//队列， 是否持久化
					exchange = @Exchange(name = "exchange-1",//交换机
					durable = "true",//是否持久化
					type = "topic",//模式
					ignoreDeclarationExceptions = "true"),
					key = "springboot.*"//路由key
				)
			)
	@RabbitHandler
	public void onMessage(Message message, Channel channel) throws Exception {
		//	1. 收到消息以后进行业务端消费处理
		System.err.println("-----------------------");
		System.err.println("消费消息:" + message.getPayload());

		//  2. 处理成功之后 获取deliveryTag 并进行手工的ACK操作, 因为我们配置文件里配置的是 手工签收
		//	spring.rabbitmq.listener.simple.acknowledge-mode=manual
		Long deliveryTag = (Long)message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);
		channel.basicAck(deliveryTag, false);
	}



}
