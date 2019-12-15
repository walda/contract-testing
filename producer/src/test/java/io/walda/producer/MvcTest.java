package io.walda.producer;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.walda.producer.config.MessagingTemplate;
import io.walda.producer.controller.MessageDto;
import io.walda.producer.controller.ProducerController;
import io.walda.producer.service.ProducerService;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;

import java.sql.Timestamp;
import java.util.TimeZone;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMessageVerifier
public abstract class MvcTest {

    @LocalServerPort
    private int port;

    @Autowired
    private MessagingTemplate messagingTemplate;

    @Mock
    private ProducerService producerService;

    @InjectMocks
    private ProducerController producerController;

    @Before
    public void setup() {
        StandaloneMockMvcBuilder standaloneMockMvcBuilder
                = MockMvcBuilders.standaloneSetup(producerController);
        RestAssuredMockMvc.standaloneSetup(standaloneMockMvcBuilder);
    }

    public void messageHello() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        MessageDto messageDto = new MessageDto(1, "Peter", "John", "Hello John !!!",
                Timestamp.valueOf("2019-12-14 22:13:40"));

        messagingTemplate.convertAndSend("producer","send", messageDto);
    }
}
