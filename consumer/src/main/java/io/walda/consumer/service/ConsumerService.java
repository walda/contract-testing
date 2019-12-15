package io.walda.consumer.service;

import io.walda.consumer.controller.MessageDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {

    @RabbitListener(queues = "producer.message")
    public void onMessage(MessageDto messageDto) {
        System.out.println(messageDto);
    }

}
