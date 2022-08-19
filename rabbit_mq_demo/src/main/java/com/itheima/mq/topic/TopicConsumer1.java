package com.itheima.mq.topic;

import com.itheima.mq.utils.ConnectionUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @ClassName SimpleConsumer
 * @Description 工作队列：消费者1
 * @Author 传智播客
 * @Date 10:53 2019/8/9
 * @Version 2.1
 **/
public class TopicConsumer1 {

    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtils.getConnection();
        // 8.创建消息通道
        Channel channel = connection.createChannel();
        // 9.创建队列
        // arg0：队列名称
        // arg1：是否持久化
        // arg2：是否排外
        // arg3：关闭连接时队列是否自动删除
        // arg4：队列其他参数
        channel.queueDeclare("topic_queue1", true, false, false, null);
        // 10.创建消费者，并设置消息处理
        DefaultConsumer consume = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                // envelope：包含了消息的属性
                long id = envelope.getDeliveryTag();    // 消息id
                String exchange = envelope.getExchange(); // 交换机
                String routingKey = envelope.getRoutingKey(); // 路由的key
                String message = new String(body, "UTF-8");
                System.out.println("消息id：" + id + "---交换机：" + exchange + "---路由：" + routingKey + "---消息体：" + message);
                // TODO：具体的业务当中，获取到了消息体，处理相关的业务
            }
        };
        // 11.消息监听
        // arg0：监听的队列名称
        // arg1：是否自动应答，设置为true为表示消息接收到自动向mq回复接收到了，mq接收到回复会删除消息，设置为false则需要手动确认
        // arg2：消费者接收消息到后回调（消费消息）
        channel.basicConsume("topic_queue1", true, consume);
        // 12.关闭资源(不建议关闭，建议一直监听消息)
    }
}
