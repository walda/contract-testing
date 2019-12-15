package io.walda.consumer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProducerConfig {

    @Bean("producer.message")
    public Queue producerMessage() {
        return QueueBuilder
                .durable("producer.message")
                .build();
    }

    @Bean("producer")
    public DirectExchange producer() {
        return (DirectExchange) ExchangeBuilder
                .directExchange("producer")
                .build();
    }

    @Bean
    public Binding producerBinding(@Qualifier("producer") DirectExchange directExchange,
                                   @Qualifier("producer.message") Queue transactionQueue) {
        return BindingBuilder
                .bind(transactionQueue)
                .to(directExchange).with("send");
    }

}
