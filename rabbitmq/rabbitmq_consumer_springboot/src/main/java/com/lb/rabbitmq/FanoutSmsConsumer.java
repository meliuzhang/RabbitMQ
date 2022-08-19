package com.lb.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author LB
 * @Remarks
 * @date 2019/12/07 18:02
 */
//第二个队列
@Component
@RabbitListener(queues = "fanout_sms_queue")
public class FanoutSmsConsumer {
    @RabbitHandler
    public void process(String msg) {
        System.out.println("第二个消费者获取生产者消息msg:" + msg);
    }
}
