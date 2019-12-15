package producer

import org.springframework.cloud.contract.spec.Contract
Contract.make {
    request{
        method GET()
        url("/sayHello") {
        }
    }
    response {
        body(
            id: value(producer(anyNumber()), consumer("1")),
            from: "Peter",
            to: "John",
            text: "Hello John !!!",
            timestamp: value(producer(regex('[0-9]{13}')), consumer(1576359936391))
        )
        status 200
        headers {
            header 'Content-Type': 'application/json;charset=UTF-8'
        }
    }
}