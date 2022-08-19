package com.lb.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author LB
 * @Remarks
 * @date 2019/12/07 18:01
 */
//第一个队列
@Component
@RabbitListener(queues = "fanout_eamil_queue")
public class FanoutEamilConsumer {
    @RabbitHandler
    public void process(String msg) throws Exception {
        System.out.println("第一个消费者获取生产者消息msg:" + msg);
        int a = 1/0;
    }
}
