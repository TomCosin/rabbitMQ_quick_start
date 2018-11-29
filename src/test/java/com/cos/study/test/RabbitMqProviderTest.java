package com.cos.study.test;

import com.cosin.MySpringBootApplication;
import com.cosin.config.RabbitConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = MySpringBootApplication.class)
@RunWith(SpringRunner.class)
public class RabbitMqProviderTest {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Test
    public void testSendByTopics(){
        for (int i=0;i<10;i++){
            String message = "a inform to user"+i;
            rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_TOPICS_INFORM,"inform.a.b",message);
            System.out.println("Send Message is:'" + message + "'");
        }
    }

}
