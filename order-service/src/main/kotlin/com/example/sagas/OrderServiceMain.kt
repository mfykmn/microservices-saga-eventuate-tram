package com.example.sagas

import io.eventuate.tram.spring.consumer.kafka.EventuateTramKafkaMessageConsumerConfiguration
import io.eventuate.tram.spring.messaging.producer.jdbc.TramMessageProducerJdbcConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import
import org.springframework.context.annotation.Configuration
import io.eventuate.tram.sagas.spring.orchestration.SagaOrchestratorConfiguration

@SpringBootApplication
@Configuration
@Import(
    SagaOrchestratorConfiguration::class,
    TramMessageProducerJdbcConfiguration::class,
    EventuateTramKafkaMessageConsumerConfiguration::class)
class OrderServiceMain

fun main(args: Array<String>) {
    runApplication<OrderServiceMain>(*args)
}
