package com.example.sagas.sagas.createorder

import com.example.sagas.domain.entity.RejectionReason
import com.example.sagas.domain.repository.OrderRepository
import io.eventuate.tram.commands.consumer.CommandWithDestination
import io.eventuate.tram.commands.consumer.CommandWithDestinationBuilder
import io.eventuate.tram.sagas.orchestration.SagaDefinition
import io.eventuate.tram.sagas.simpledsl.SimpleSaga
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

object PaymentServiceChannels {
    const val COMMAND_CHANNEL = "paymentService"
}

@Component
class CreateOrderSaga : SimpleSaga<CreateOrderSagaData> {

    @Autowired
    lateinit var orderRepository: OrderRepository

    private val sagaDefinition: SagaDefinition<CreateOrderSagaData> =
        step()
            .invokeLocal(::create)
            .withCompensation(::reject)
        .step()
            .invokeParticipant(::reserveInvoice)
            .onReply(InvoiceNotFound::class.java, ::handleInvoiceNotFound)
            .onReply(InvoiceLimitExceeded::class.java, ::handleInvoiceLimitExceeded)
        .step()
            .invokeLocal(::approve)
        .build()

    override fun getSagaDefinition(): SagaDefinition<CreateOrderSagaData> {
        return sagaDefinition
    }

    private fun handleInvoiceNotFound(
        data: CreateOrderSagaData,
        reply: InvoiceNotFound
    ) {
        data.rejectionReason = RejectionReason.UNKNOWN_CUSTOMER
        reject(data)
    }

    private fun handleInvoiceLimitExceeded(
        data: CreateOrderSagaData,
        reply: InvoiceLimitExceeded
    ) {
        data.rejectionReason = RejectionReason.INSUFFICIENT_CREDIT
        reject(data)
    }

    private fun create(data: CreateOrderSagaData) {
        println("CreateOrderSaga::create")
        orderRepository.save(data.order)
    }

    fun reserveInvoice(data: CreateOrderSagaData): CommandWithDestination {
        println("CreateOrderSaga::reserveInvoice")
        return CommandWithDestinationBuilder.send(
            ReserveInvoiceCommand(data.order.orderId)
        )
            .to(PaymentServiceChannels.COMMAND_CHANNEL)
            .build()
    }

    private fun approve(data: CreateOrderSagaData) {
        println("CreateOrderSaga::approve")
        val order = orderRepository.findById(data.order.orderId).get()
        order.approve()
        orderRepository.save(order)
    }

    private fun reject(data: CreateOrderSagaData) {
        println("CreateOrderSaga::reject")
        val order = orderRepository.findById(data.order.orderId).get()
        order.reject(data.rejectionReason!!)
        orderRepository.save(order)
    }
}
