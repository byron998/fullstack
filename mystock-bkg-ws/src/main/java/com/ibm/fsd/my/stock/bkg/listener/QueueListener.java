package com.ibm.fsd.my.stock.bkg.listener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;


@Component
@RabbitListener(queues = "helloQueue")
public class QueueListener {

    @RabbitHandler
    public void displayMail(String msg, Channel channel, Message message) throws Exception {
        System.out.println("队列监听器1号收到消息" + msg);
        //channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);//如果需要确认的要调用
    }
}
