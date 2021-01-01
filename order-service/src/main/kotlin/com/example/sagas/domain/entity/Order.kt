package com.example.sagas.domain.entity

import java.math.BigDecimal
import java.util.*
import javax.persistence.Entity
import javax.persistence.Table
import javax.persistence.Id
import javax.persistence.Column

typealias OrderId = String

enum class OrderStatus {
    CREATED, SHIPPED, REJECTED
}

enum class ItemType {
    LAPTOP, HEADPHONE, SMARTPHONE
}

@Entity
@Table(name="orders")
class Order(
    @Id
    @Column(name="order_id")
    val orderId: OrderId,

    @Column(name="item_type")
    var itemType: String,

    @Column(name="price")
    var price: BigDecimal,

    @Column(name="currency")
    var currency: String,

    @Column(name="order_status")
    var orderStatus: String) {

    constructor(_itemType: ItemType, _price: BigDecimal, _currency: String) : this(
        UUID.randomUUID().toString(),
        _itemType.toString(),
        _price,
        _currency,
        OrderStatus.CREATED.toString())

    fun shipped() {
        this.orderStatus = OrderStatus.SHIPPED.toString()
    }

    fun rejected() {
        this.orderStatus = OrderStatus.REJECTED.toString()
    }
}
