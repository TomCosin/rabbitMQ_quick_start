package com.cosin.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    /**
     * 队列名
     */
    public static final String QUEUE_INFORM_A = "queue_inform_A";
    public static final String QUEUE_INFORM_B = "queue_inform_B";
    /**
     * 交换机名
     */
    public static final String EXCHANGE_TOPICS_INFORM = "exchange_topics_inform";

    /**
     * 交换机配置
     * ExchangeBuilder提供了fanout、direct、topic、header交换机类型的配置
     *
     * @return the exchange
     */
    @Bean(EXCHANGE_TOPICS_INFORM)
    public Exchange EXCHANGE_TOPICS_INFORM() {
        //durable(true)持久化，消息队列重启后交换机仍然存在
        return ExchangeBuilder.topicExchange(EXCHANGE_TOPICS_INFORM).durable(true).build();
    }

    /**
     * 声明队列
     *
     * @return
     */
    @Bean(QUEUE_INFORM_A)
    public Queue QUEUE_INFORM_A() {
        Queue queue = new Queue(QUEUE_INFORM_A);
        return queue;
    }

    /**
     * 声明队列
     *
     * @return
     */
    @Bean(QUEUE_INFORM_B)
    public Queue QUEUE_INFORM_B() {
        Queue queue = new Queue(QUEUE_INFORM_B);
        return queue;
    }

    /** channel.queueBind(INFORM_QUEUE_A,"inform_exchange_topic","inform.#.a.#");
     * 绑定队列到交换机 .
     *
     * @param queue the queue
     * @param exchange the exchange
     * @return the binding
     */
    @Bean
    public Binding BINDING_QUEUE_INFORM_SMS(@Qualifier(QUEUE_INFORM_A) Queue queue,
                                            @Qualifier(EXCHANGE_TOPICS_INFORM) Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("inform.#.a.#").noargs();
    }

    @Bean
    public Binding BINDING_QUEUE_INFORM_EMAIL(@Qualifier(QUEUE_INFORM_B) Queue queue,
                                              @Qualifier(EXCHANGE_TOPICS_INFORM) Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("inform.#.b.#").noargs();
    }

}
