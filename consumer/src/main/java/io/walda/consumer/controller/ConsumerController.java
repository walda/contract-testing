package io.walda.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.time.Instant;

@RestController
public class ConsumerController {

    @Autowired
    private ProducerClient producerClient;

    @GetMapping(value ="sendMessage")
    public MessageResponseDto sendMessage() {
        MessageDto messageDto = producerClient.sayHello();
        return new MessageResponseDto(Timestamp.from(Instant.now()), messageDto);
    }

}
