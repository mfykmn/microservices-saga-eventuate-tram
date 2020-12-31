package com.example.sagas.domain.saga

import com.example.sagas.domain.entity.OrderStatus
import com.example.sagas.application.commands.CreateInvoiceCommand
import com.example.sagas.application.commands.CreateShippingCommand
import com.example.sagas.application.commands.UpdateOrderStatusCommand
import com.example.sagas.application.events.*
import com.progressivecoder.ordermanagement.orderservice.aggregates.OrderStatus
import java.util.*
import javax.inject.Inject
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.modelling.saga.SagaEventHandler
import org.axonframework.modelling.saga.SagaLifecycle
import org.axonframework.modelling.saga.StartSaga
import org.axonframework.spring.stereotype.Saga


@Saga
class OrderManagementSaga {
    @Inject
    @Transient
    private val commandGateway: CommandGateway? = null
    @StartSaga
    @SagaEventHandler(associationProperty = "orderId")
    fun handle(orderCreatedEvent: OrderCreatedEvent) {
        val paymentId = UUID.randomUUID().toString()
        println("Saga invoked")

        //associate Saga
        SagaLifecycle.associateWith("paymentId", paymentId)
        System.out.println("order id" + orderCreatedEvent.orderId)

        //send the commands
        commandGateway!!.send(CreateInvoiceCommand(paymentId, orderCreatedEvent.orderId))
    }

    @SagaEventHandler(associationProperty = "paymentId")
    fun handle(invoiceCreatedEvent: InvoiceCreatedEvent) {
        val shippingId = UUID.randomUUID().toString()
        println("Saga continued")

        //associate Saga with shipping
        SagaLifecycle.associateWith("shipping", shippingId)

        //send the create shipping command
        commandGateway!!.send(
            CreateShippingCommand(
                shippingId,
                invoiceCreatedEvent.orderId,
                invoiceCreatedEvent.paymentId
            )
        )
    }

    @SagaEventHandler(associationProperty = "orderId")
    fun handle(orderShippedEvent: OrderShippedEvent) {
        commandGateway!!.send(
            UpdateOrderStatusCommand(
                orderShippedEvent.orderId,
                OrderStatus.SHIPPED.toString()
            )
        )
    }

    @SagaEventHandler(associationProperty = "orderId")
    fun handle(orderUpdatedEvent: OrderUpdatedEvent?) {
        SagaLifecycle.end()
    }
}
