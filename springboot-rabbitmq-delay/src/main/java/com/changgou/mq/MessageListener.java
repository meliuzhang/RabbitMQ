package com.changgou.mq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author LB
 * @Remarks 监听消息
 * @date 2019/08/31 10:51
 */
@Component
@RabbitListener(queues ="message.queue" )//监听的队列
public class MessageListener {
    @RabbitHandler
    public void readMessage(String msg){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = dateFormat.format(new Date());//时间转换成字符串格式
        System.out.println("当前接收时间:"+date);
        System.out.println("消费的消息:"+msg);
    }
}
