package io.walda.producer.controller;

import io.walda.producer.service.ProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.time.Instant;

@RestController
@RequiredArgsConstructor
public class ProducerController {

    private final ProducerService producerService;

    @GetMapping(value = "sayHello", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageDto> sayHello() {
        MessageDto messageDto = new MessageDto(1, "Peter", "John", "Hello John !!!",
                Timestamp.from(Instant.now()));

        producerService.sendMessage(messageDto);

        return new ResponseEntity<>(messageDto, HttpStatus.OK);
    }
}
