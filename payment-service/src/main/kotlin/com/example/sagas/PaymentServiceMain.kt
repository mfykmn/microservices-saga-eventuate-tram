package com.example.sagas

import io.eventuate.tram.spring.consumer.kafka.EventuateTramKafkaMessageConsumerConfiguration
import io.eventuate.tram.spring.messaging.producer.jdbc.TramMessageProducerJdbcConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Bean
import com.example.sagas.sagas.createorder.InvoiceServiceCommandHandler
import io.eventuate.tram.sagas.participant.SagaCommandDispatcherFactory
import io.eventuate.tram.commands.consumer.CommandDispatcher

@SpringBootApplication
@Configuration
@Import(TramMessageProducerJdbcConfiguration::class,
    EventuateTramKafkaMessageConsumerConfiguration::class)
class PaymentServiceMain

//@Bean
//fun invoiceCommandHandler(): InvoiceServiceCommandHandler {
//    return InvoiceServiceCommandHandler()
//}

@Bean
fun invoiceCommandDispatcher(
    target: InvoiceServiceCommandHandler,
    sagaCommandDispatcherFactory: SagaCommandDispatcherFactory
): CommandDispatcher {
    return sagaCommandDispatcherFactory.make(
        "invoiceCommandDispatcher",
        target.commandHandlerDefinitions()
    )
}

fun main(args: Array<String>) {
    runApplication<PaymentServiceMain>(*args)
}
