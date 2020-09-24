package com.rabbitmq.util;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Classname RabbitMqConnectionUtil
 * @Author 刘春良
 * @Date 2020/9/24 0024
 */
public class RabbitMqUtil {
    // 连接工具方法
    public static ConnectionFactory Connection() throws IOException, TimeoutException {
        //打开连接和创建频道，与发送端一样
        ConnectionFactory factory = new ConnectionFactory();
        //设置RabbitMQ所在主机ip或者主机名
        factory.setHost("192.168.233.133");
        factory.setPort(5672);
        factory.setUsername("admin");
        factory.setPassword("admin");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        return factory;
    }
    // 关闭连接方法
    public static void close(Channel channel,Connection connection ) throws IOException, TimeoutException {
        //关闭频道和连接
        channel.close();
        connection.close();
    }
}
