package com.itheima.mq.routing;

import com.itheima.mq.utils.ConnectionUtils;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * @ClassName SimpleProducer
 * @Description ps：fanout:direct
 * @Author 传智播客
 * @Date 10:30 2019/8/9
 * @Version 2.1
 **/
public class RoutingProducer {

    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        // 创建两个队列
        // arg0：队列名称
        // arg1：是否持久化
        // arg2：是否排外
        // arg3：关闭连接时队列是否自动删除
        // arg4：队列其他参数
        channel.queueDeclare("routing_queue1", true, false, false, null);
        channel.queueDeclare("routing_queue2", true, false, false, null);
        // 创建交换机：arg0：交换机名称   arg1：交换机类型 DIRECT，直连
        channel.exchangeDeclare("routing_exchange", BuiltinExchangeType.DIRECT);
        // 将交换机与队列进行绑定
        channel.queueBind("routing_queue1", " ", "error");
        channel.queueBind("routing_queue2", "routing_exchange", "error");
        channel.queueBind("routing_queue2", "routing_exchange", "info");
        channel.queueBind("routing_queue2", "routing_exchange", "warning");
        for (int i = 1; i <= 10; i++){
            String routingKey = "";
            // 10.创建消息
            String msssage = "你的学号：" + i;
            // 模拟业务：不同的消息发送到不同的routingKey的队列中
            if(i % 2 == 0){
                routingKey = "error";   // routing_queue1、routing_queue2:2,4,6,8,10
            }else if (i % 5 == 0){
                routingKey = "info";    // routing_queue2:5
            }else {
                routingKey = "warning"; // routing_queue2：1,3,7,9
            }
            msssage += routingKey;
            // 11.消息发送
            // arg0：交换机名称，没有指定使用默认的Default Exchange
            // arg1：路由key，点对点模式可以使用队列名称
            // arg2：指定消息其他属性
            // arg3：消息的字节码
            channel.basicPublish("routing_exchange", routingKey, null, msssage.getBytes("UTF-8"));
        }
        // 12.关闭资源
        channel.close();
        connection.close();
    }
}
