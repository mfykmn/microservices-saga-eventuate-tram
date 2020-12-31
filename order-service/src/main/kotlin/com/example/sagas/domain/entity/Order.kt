package com.example.sagas.domain.entity

import java.math.BigDecimal
import org.axonframework.modelling.command.TargetAggregateIdentifier

typealias OrderId = String

enum class OrderStatus {
    CREATED, SHIPPED, REJECTED
}

enum class ItemType {
    LAPTOP, HEADPHONE, SMARTPHONE
}

data class Order(
    @TargetAggregateIdentifier val orderId: OrderId,
    val itemType: ItemType,
    val price: BigDecimal,
    val currency: String,
    val orderStatus: OrderStatus,
)
