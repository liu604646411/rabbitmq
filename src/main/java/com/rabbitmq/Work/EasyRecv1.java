package com.rabbitmq.Work;

import com.rabbitmq.client.*;
import com.rabbitmq.util.RabbitMqUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Classname EasyRecv
 * @Author 刘春良
 * @Date 2020/9/23 0023
 * 轮询消费者1
 */

public class EasyRecv1 {
    //队列名称
    private final static String QUEUE_NAME = "hello world";
    public static void main(String[] argv) throws IOException, InterruptedException, TimeoutException {
        //打开连接和创建频道，与发送端一样
        ConnectionFactory factory = RabbitMqUtil.Connection();
        //声明队列，主要为了防止消息接收者先运行此程序，队列还不存在时创建队列。
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        /**
         * 队列名
         * 是否持久化
         *  是否排外  即只允许该channel访问该队列   一般等于true的话用于一个队列只能有一个消费者来消费的场景
         *  是否自动删除  消费完删除
         *  其他属性
         *
         */
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println("Waiting for messages. To exit press CTRL+C");

        Consumer consumer = new DefaultConsumer( channel ) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("Customer Received '" + message + "'");
            }
        };
        //自动回复队列应答 -- RabbitMQ中的消息确认机制
        channel.basicConsume(QUEUE_NAME, true, consumer);
    }
}
