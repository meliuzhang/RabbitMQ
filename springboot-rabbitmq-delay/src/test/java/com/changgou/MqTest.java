package com.changgou;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author LB
 * @Remarks
 * @date 2019/08/31 10:55
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MqTest {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void contextLoad() throws IOException {
        rabbitTemplate.convertAndSend("delay.message.queue", (Object) "欢迎来到黑马程序员", new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setExpiration(1000*10+"");
                return message;
            }
        });
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("发送当前时间:"+dateFormat.format(new Date()));
        System.in.read();
    }

    @Test
    public void contextLoads(){
        rabbitTemplate.convertAndSend("dlx.exchange","message.queue", "插入");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("当前发送时间:"+dateFormat.format(new Date()));
    }

}
