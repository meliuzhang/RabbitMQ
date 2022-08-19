package com.lb.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author LB
 * @Remarks
 * @date 2019/12/07 17:55
 */
@Service
public class FanoutProducer {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send(String queueName){
        String msg = "my_fanout_msg:" + new Date();
        System.out.println(msg+":"+msg);
        amqpTemplate.convertAndSend(queueName,msg);
    }

}
