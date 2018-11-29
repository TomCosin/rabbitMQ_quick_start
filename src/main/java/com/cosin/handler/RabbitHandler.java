package com.cosin.handler;

import com.cosin.config.RabbitConfig;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitHandler {

    /**
     * 监听a队列
     */
    @RabbitListener(queues = {RabbitConfig.QUEUE_INFORM_A})
    public void receiveA(String msg, Message message, Channel channel){
        System.out.println(msg);
    }

    /**
     * 监听b队列
     */
    @RabbitListener(queues = {RabbitConfig.QUEUE_INFORM_B})
    public void receiveB(String msg,Message message,Channel channel){
        System.out.println(msg);
    }

}
