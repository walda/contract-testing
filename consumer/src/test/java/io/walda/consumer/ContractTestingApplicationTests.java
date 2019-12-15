package io.walda.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.walda.consumer.controller.MessageDto;
import io.walda.consumer.controller.MessageResponseDto;
import io.walda.consumer.service.ConsumerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.cloud.contract.stubrunner.StubTrigger;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT, classes = ContractTestingApplication.class)
@AutoConfigureStubRunner(ids = {"io.walda:producer:+:9090"},
		stubsMode = StubRunnerProperties.StubsMode.LOCAL)
@AutoConfigureMessageVerifier
@AutoConfigureMockMvc
public class ContractTestingApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private StubTrigger stubTrigger;

	@Autowired
	private ConsumerService consumerService;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void receiveMessage() {

		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(byteArrayOutputStream));

		stubTrigger.trigger("messageHello");

		assertThat(byteArrayOutputStream.toString().replaceAll("\r", ""))
				.isEqualTo("MessageDto(id=1, from=Peter, to=John, text=Hello John !!!)\n");
	}

	@Test
	public void sendMessage() throws Exception {
		MvcResult result =
				mockMvc.perform(get("/sendMessage")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn();

		MessageResponseDto responseDto =
				new ObjectMapper().readValue(result.getResponse().getContentAsByteArray(), MessageResponseDto.class);
		assertThat(responseDto.getMessage()).isEqualTo(new MessageDto(1, "Peter", "John", "Hello John !!!"));

	}

}
