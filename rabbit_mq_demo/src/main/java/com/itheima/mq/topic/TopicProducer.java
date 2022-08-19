package com.itheima.mq.topic;

import com.itheima.mq.utils.ConnectionUtils;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * @ClassName SimpleProducer
 * @Description ps：fanout:topic
 * @Author 传智播客
 * @Date 10:30 2019/8/9
 * @Version 2.1
 **/
public class TopicProducer {

    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        // 创建两个队列
        // arg0：队列名称
        // arg1：是否持久化
        // arg2：是否排外
        // arg3：关闭连接时队列是否自动删除
        // arg4：队列其他参数
        channel.queueDeclare("topic_queue1", true, false, false, null);
        channel.queueDeclare("topic_queue2", true, false, false, null);
        // 创建交换机：arg0：交换机名称   arg1：交换机类型 TOPIC，通配符模式
        channel.exchangeDeclare("topic_exchange", BuiltinExchangeType.TOPIC);
        // 将交换机与队列进行绑定
        channel.queueBind("topic_queue1", "topic_exchange", "item.update");
        channel.queueBind("topic_queue1", "topic_exchange", "item.delete");
        channel.queueBind("topic_queue2", "topic_exchange", "item.*");

        // 发送消息
        // 11.消息发送
        // arg0：交换机名称，没有指定使用默认的Default Exchange
        // arg1：路由key，点对点模式可以使用队列名称
        // arg2：指定消息其他属性
        // arg3：消息的字节码
        String update_message = "对商品进行更新操作。。。update";
        channel.basicPublish("topic_exchange", "item.update", null, update_message.getBytes("UTF-8"));

        String delete_message = "对商品进行删除操作。。。delete";
        channel.basicPublish("topic_exchange", "item.delete", null, delete_message.getBytes("UTF-8"));

        String insert_message = "对商品进行新增操作。。。insert";
        channel.basicPublish("topic_exchange", "item.insert", null, insert_message.getBytes("UTF-8"));

        String select_message = "对商品进行查询操作。。。select";
        channel.basicPublish("topic_exchange", "item.select", null, select_message.getBytes("UTF-8"));



        // 12.关闭资源
        channel.close();
        connection.close();
    }
}
