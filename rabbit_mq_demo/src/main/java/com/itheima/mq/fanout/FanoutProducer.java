package com.itheima.mq.fanout;

import com.itheima.mq.utils.ConnectionUtils;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * @ClassName SimpleProducer
 * @Description ps：fanout:广播
 * @Author 传智播客
 * @Date 10:30 2019/8/9
 * @Version 2.1
 **/
public class FanoutProducer {

    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        // 创建两个队列
        // arg0：队列名称
        // arg1：是否持久化
        // arg2：是否排外
        // arg3：关闭连接时队列是否自动删除
        // arg4：队列其他参数
        channel.queueDeclare("fanout_queue1", true, false, false, null);
        channel.queueDeclare("fanout_queue2", true, false, false, null);
        // 创建交换机：
        // arg0：交换机名称
        // arg1：交换机类型 fanout，广播
        channel.exchangeDeclare("fanout_exchange", BuiltinExchangeType.FANOUT);
        // 将交换机与队列进行绑定
        channel.queueBind("fanout_queue1", "fanout_exchange", "");
        channel.queueBind("fanout_queue2", "fanout_exchange", "");
        for (int i = 1; i <= 10; i++){
            // 10.创建消息
            String msssage = "你的学号：" + i;
            // 11.消息发送
            channel.basicPublish("fanout_exchange", "", null, msssage.getBytes("UTF-8"));
        }
        // 12.关闭资源
        channel.close();
        connection.close();
    }
}
