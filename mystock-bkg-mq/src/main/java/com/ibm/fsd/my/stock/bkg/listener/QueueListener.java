package com.ibm.fsd.my.stock.bkg.listener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.ibm.fsd.my.stock.bkg.bean.Mail;
import com.rabbitmq.client.AMQP.Channel;

@Component
@RabbitListener(queues = "helloQueue")
public class QueueListener {

    @RabbitHandler
    public void displayMail(Mail mail, Channel channel, Message message) throws Exception {
        System.out.println("队列监听器1号收到消息" + mail.toString());
        //channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);//如果需要确认的要调用
    }
}
