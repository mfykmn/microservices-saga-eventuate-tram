package com.example.sagas.application

import com.example.sagas.domain.entity.Order
import com.example.sagas.domain.entity.OrderId
import java.util.concurrent.CompletableFuture


interface OrderService {
    fun createOrder(): CompletableFuture<String?>?
    fun findById(orderId: OrderId) : Order?
}
