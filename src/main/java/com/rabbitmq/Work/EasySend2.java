package com.rabbitmq.Work;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.util.RabbitMqUtil;

import java.util.Scanner;

/**
 * @Classname EasySend
 * @Author 刘春良
 * @Date 2020/9/23 0023
 */
public class EasySend2 {
    //队列名称
    private final static String QUEUE_NAME = "hello world";

    public static void main(String[] argv) throws Exception {
        /**
         * 创建连接连接到MabbitMQ
         */
        ConnectionFactory factory = RabbitMqUtil.Connection();

        while (true) {
            //创建一个连接
            Connection connection = factory.newConnection();
            //创建一个频道
            Channel channel = connection.createChannel();
            //指定一个队列
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            //发送的消息
            Scanner scanner = new Scanner(System.in);
            String ms = scanner.nextLine();
            //String message = "hello world!";
            //往队列中发出一条消息
            channel.basicPublish("", QUEUE_NAME, null, ms.getBytes());
            System.out.println("Sent '" + ms + "'");
            //关闭频道和连接
            RabbitMqUtil.close(channel,connection);
        }
    }
}
