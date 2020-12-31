package com.example.sagas.domain.entity

import java.math.BigDecimal
import com.progressivecoder.ecommerce.commands.CreateOrderCommand;
import com.progressivecoder.ecommerce.commands.UpdateOrderStatusCommand;
import com.progressivecoder.ecommerce.events.OrderCreatedEvent;
import com.progressivecoder.ecommerce.events.OrderUpdatedEvent;
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.spring.stereotype.Aggregate





@Aggregate
class OrderAggregate {
    @AggregateIdentifier
    private var orderId: String? = null
    private var itemType: ItemType? = null
    private var price: BigDecimal? = null
    private var currency: String? = null
    private var orderStatus: OrderStatus? = null

    constructor() {}

    @CommandHandler
    constructor(createOrderCommand: CreateOrderCommand) {
        AggregateLifecycle.apply(
            OrderCreatedEvent(
                createOrderCommand.orderId,
                createOrderCommand.itemType,
                createOrderCommand.price,
                createOrderCommand.currency,
                createOrderCommand.orderStatus
            )
        )
    }

    @EventSourcingHandler
    protected fun on(orderCreatedEvent: OrderCreatedEvent) {
        orderId = orderCreatedEvent.orderId
        itemType = ItemType.valueOf(orderCreatedEvent.itemType)
        price = orderCreatedEvent.price
        currency = orderCreatedEvent.currency
        orderStatus = OrderStatus.valueOf(orderCreatedEvent.orderStatus)
    }

    @CommandHandler
    protected fun on(updateOrderStatusCommand: UpdateOrderStatusCommand) {
        AggregateLifecycle.apply(
            OrderUpdatedEvent(
                updateOrderStatusCommand.orderId,
                updateOrderStatusCommand.orderStatus
            )
        )
    }

    @EventSourcingHandler
    protected fun on(orderUpdatedEvent: OrderUpdatedEvent) {
        orderId = orderId
        orderStatus = OrderStatus.valueOf(orderUpdatedEvent.orderStatus)
    }
}
