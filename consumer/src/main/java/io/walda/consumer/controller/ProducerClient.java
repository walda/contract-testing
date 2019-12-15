package io.walda.consumer.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "ProducerClient", url = "http://localhost:9090")
public interface ProducerClient {

    @RequestMapping(method = RequestMethod.GET, value = "/sayHello", consumes = MediaType.APPLICATION_JSON_VALUE)
    MessageDto sayHello();
}
