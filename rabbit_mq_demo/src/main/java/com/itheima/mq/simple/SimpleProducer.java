package com.itheima.mq.simple;

import com.itheima.mq.utils.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName SimpleProducer
 * @Description 简单队列：生产者
 * @Author 传智播客
 * @Date 10:30 2019/8/9
 * @Version 2.1
 **/
public class SimpleProducer {

    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtils.getConnection();
        // 8.创建消息通道
        Channel channel = connection.createChannel();
        // 9.创建队列
        /**
         * arg0：指定的队列名称
         * arg1：是否持久化
         * arg2：是否排外（操作该队列时候是否允许其他人操作）
         * arg3：是否自动删除队列（队列是否完成后）关闭连接时队列是否自动删除
         * arg4：队列其他参数
         */
        channel.queueDeclare("simple_queue", true, false, false, null);
        // 10.创建消息
        String msssage = "欢迎学习mq。。。";
        // 11.消息发送
        /**
         * arg0：指定的交换机
         * arg1：指定路由器的key
         * arg2：null
         * arg3：消息体
         */
        channel.basicPublish("", "simple_queue", null, msssage.getBytes("UTF-8"));
        // 12.关闭资源
        channel.close();
        connection.close();
    }
}
//arg4:队列其他参数设置
//        - Message TTL 设置消息生命周期
//        - Auto Expire 当队列在指定的时间没有被访问就会被删除
//        - Max Length 限定队列的消息的最大值长度，超过指定长度将会把最早的几条删除掉
//        - Max Length Bytes 限定队列最大占用的空间大小
//        - DLX 当队列消息长度大于最大长度、或者过期的等，将从队列中删除的消息推送到指定的交换机中去而不是丢弃掉
//        - DLK 将删除的消息推送到指定交换机的指定路由键的队列中去
//        - Maximum priority 声明优先级队列
//        - Lazy mode 先将消息保存到磁盘上，不放在内存中，当消费者开始消费的时候才加载到内存中