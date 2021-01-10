package com.example.sagas.sagas.createorder

import com.example.sagas.application.PaymentService
import org.springframework.beans.factory.annotation.Autowired
import io.eventuate.tram.commands.consumer.CommandHandlers
import io.eventuate.tram.commands.consumer.CommandMessage
import io.eventuate.tram.commands.consumer.CommandHandlerReplyBuilder.withFailure
import io.eventuate.tram.commands.consumer.CommandHandlerReplyBuilder.withSuccess
import io.eventuate.tram.messaging.common.Message
import io.eventuate.tram.sagas.participant.SagaCommandHandlersBuilder
import org.springframework.stereotype.Component

object PaymentServiceChannels {
    const val COMMAND_CHANNEL = "paymentService"
}

class CustomerNotFoundException : RuntimeException()
class CustomerCreditLimitExceededException : RuntimeException()

@Component
class InvoiceServiceCommandHandler {
    @Autowired
    lateinit var paymentService: PaymentService

    fun commandHandlerDefinitions(): CommandHandlers {
        return SagaCommandHandlersBuilder
            .fromChannel(PaymentServiceChannels.COMMAND_CHANNEL)
            .onMessage(ReserveInvoiceCommand::class.java, ::reserveInvoice)
            .build()
    }

    private fun reserveInvoice(cm: CommandMessage<ReserveInvoiceCommand>): Message {
        return try {
            paymentService.reserveInvoice(cm.command.orderId)
            withSuccess(InvoiceReserved())
        } catch (e: CustomerNotFoundException) {
            withFailure(InvoiceNotFound())
        } catch (e: CustomerCreditLimitExceededException) {
            withFailure(InvoiceLimitExceeded())
        }
    }
}
