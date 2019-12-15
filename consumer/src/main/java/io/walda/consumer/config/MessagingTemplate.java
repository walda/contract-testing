package io.walda.consumer.config;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class MessagingTemplate extends RabbitTemplate {

    MessagingTemplate(ConnectionFactory connectionFactory) {
        super(connectionFactory);
    }

}
