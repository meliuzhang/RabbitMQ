package com.itheima.mq.work;

import com.itheima.mq.utils.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * @ClassName SimpleProducer
 * @Description 工作队列：生产者
 * @Author 传智播客
 * @Date 10:30 2019/8/9
 * @Version 2.1
 **/
public class WorkProducer {

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
        channel.queueDeclare("work_queue", true, false, false, null);
        for (int i = 1; i <= 10; i++){
            // 10.创建消息
            String msssage = "你的学号：" + i;
            // 11.消息发送
            // arg0：交换机名称，没有指定使用默认的Default Exchange
            // arg1：路由key，点对点模式可以使用队列名称
            // arg2：指定消息其他属性
            // arg3：消息的字节码
            channel.basicPublish("", "work_queue", null, msssage.getBytes("UTF-8"));
        }
        // 12.关闭资源
        channel.close();
        connection.close();
    }
}
