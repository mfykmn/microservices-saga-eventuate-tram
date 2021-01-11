package com.example.sagas.domain.entity

import java.math.BigDecimal
import java.util.*
import javax.persistence.Entity
import javax.persistence.Table
import javax.persistence.Id
import javax.persistence.Column
import javax.persistence.Enumerated
import javax.persistence.EnumType

typealias OrderId = String

enum class OrderStatus {
    CREATED, APPROVED, REJECTED
}

enum class ItemType {
    LAPTOP, HEADPHONE, SMARTPHONE
}

enum class RejectionReason {
    INSUFFICIENT_CREDIT, UNKNOWN_CUSTOMER
}

@Entity
@Table(name="orders")
class Order(
    @Id
    @Column(name="order_id")
    val orderId: OrderId,

    @Column(name="item_type")
    @Enumerated(EnumType.STRING)
    var itemType: ItemType,

    @Column(name="price")
    var price: BigDecimal,

    @Column(name="currency")
    var currency: String,

    @Column(name="order_status")
    @Enumerated(EnumType.STRING)
    var orderStatus: OrderStatus,

    @Column(name="rejection_reason")
    @Enumerated(EnumType.STRING)
    var rejectionReason: RejectionReason?
) {

    constructor(_itemType: ItemType, _price: BigDecimal, _currency: String) : this(
        UUID.randomUUID().toString(),
        _itemType,
        _price,
        _currency,
        OrderStatus.CREATED,
        null
    )

    fun approve() {
        this.orderStatus = OrderStatus.APPROVED
    }

    fun reject() {
        this.orderStatus = OrderStatus.REJECTED
    }
}
