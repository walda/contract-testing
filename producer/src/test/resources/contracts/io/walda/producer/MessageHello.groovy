package contracts.io.walda.producer

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    label 'messageHello'
    input {
        triggeredBy('messageHello()')
    }
    outputMessage {
        sentTo('producer')
        body([
                id: value(producer(anyNumber()), consumer("1")),
                from: "Peter",
                to: "John",
                text: "Hello John !!!",
                timestamp: "2019-12-14T22:13:40.000+0000"
        ])
        headers {
            header('contentType', 'application/json')
        }
    }
}