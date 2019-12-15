package io.walda.producer.service;

import io.walda.producer.config.MessagingTemplate;
import io.walda.producer.controller.MessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProducerService {

    private final MessagingTemplate messagingTemplate;

    public void sendMessage(MessageDto messageDto) {
        messagingTemplate.convertAndSend("producer","send", messageDto);
    }

}
