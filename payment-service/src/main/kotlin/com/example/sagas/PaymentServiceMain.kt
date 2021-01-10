package com.example.sagas

import io.eventuate.tram.spring.consumer.kafka.EventuateTramKafkaMessageConsumerConfiguration
import io.eventuate.tram.spring.messaging.producer.jdbc.TramMessageProducerJdbcConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import

@SpringBootApplication
@Import(
    TramMessageProducerJdbcConfiguration::class,
    EventuateTramKafkaMessageConsumerConfiguration::class)
class PaymentServiceMain

fun main(args: Array<String>) {
    runApplication<PaymentServiceMain>(*args)
}
