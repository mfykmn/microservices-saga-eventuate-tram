package com.example.sagas.application

import com.example.sagas.domain.entity.ItemType
import com.example.sagas.domain.entity.Order
import com.example.sagas.domain.entity.OrderId
import java.math.BigDecimal


interface OrderService {
    fun createOrder(itemType: ItemType, price: BigDecimal, currency: String): Order
    fun findById(orderId: OrderId) : Order?
}
