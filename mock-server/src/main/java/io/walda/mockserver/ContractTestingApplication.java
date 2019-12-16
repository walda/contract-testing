package io.walda.mockserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.contract.stubrunner.server.EnableStubRunnerServer;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;

@SpringBootApplication
@EnableStubRunnerServer
@AutoConfigureStubRunner
public class ContractTestingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContractTestingApplication.class, args);
	}

}
