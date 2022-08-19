package com.itheima.mq.utils;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @ClassName ConnectionUtils
 * @Description
 * @Author 传智播客
 * @Date 11:06 2019/8/9
 * @Version 2.1
 **/
public class ConnectionUtils {

    public static Connection getConnection() throws Exception{
        // 1.创建链接工厂对象
        ConnectionFactory connectionFactory = new ConnectionFactory();
        // 2.设置RabbitMQ服务主机地址,默认localhost
        connectionFactory.setHost("localhost");
        // 3.设置RabbitMQ服务端口,默认5672
        connectionFactory.setPort(5672);
        // 4.设置虚拟主机名字，默认/
        connectionFactory.setVirtualHost("/sz_itheima66");
        // 5.设置用户连接名，默认guest
        connectionFactory.setUsername("itheima66");
        // 6.设置链接密码，默认guest
        connectionFactory.setPassword("123456");
        // 7.创建一个新链接
        Connection connection = connectionFactory.newConnection();

        return connection;
    }
}
