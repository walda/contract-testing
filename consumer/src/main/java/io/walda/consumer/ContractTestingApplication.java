package io.walda.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ContractTestingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContractTestingApplication.class, args);
	}

}
