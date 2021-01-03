package com.example.sagas.sagas.createorder

import com.example.sagas.domain.repository.OrderRepository
import io.eventuate.tram.commands.consumer.CommandWithDestination
import io.eventuate.tram.commands.consumer.CommandWithDestinationBuilder
import io.eventuate.tram.sagas.orchestration.SagaDefinition
import io.eventuate.tram.sagas.simpledsl.SimpleSaga
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

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
            .invokeLocal(::ship)
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
    }

    private fun handleInvoiceLimitExceeded(
        data: CreateOrderSagaData,
        reply: InvoiceLimitExceeded
    ) {
        data.rejectionReason = RejectionReason.INSUFFICIENT_CREDIT
    }

    private fun create(data: CreateOrderSagaData) {
        // Orderをデータベースに永続化する
        orderRepository.save(data.order)
    }

    private fun reserveInvoice(data: CreateOrderSagaData): CommandWithDestination {
        return CommandWithDestinationBuilder.send(
            ReserveInvoiceCommand(data.order.orderId)
        )
            .to("paymentService")
            .build()
    }

    private fun approve(data: CreateOrderSagaData) {
        orderRepository.findById(data.order.orderId).get().approve()
        orderRepository.save(data.order)
    }

    private fun ship(data: CreateOrderSagaData) {
        orderRepository.findById(data.order.orderId).get().ship()
        orderRepository.save(data.order)
    }

    private fun reject(data: CreateOrderSagaData) {
        orderRepository.findById(data.order.orderId).get().rejected()
        orderRepository.save(data.order)
        // TODO:  data.rejectionReasonの扱い
    }


}
