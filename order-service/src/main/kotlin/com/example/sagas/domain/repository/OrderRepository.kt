package com.example.sagas.domain.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import com.example.sagas.domain.entity.Order
import com.example.sagas.domain.entity.OrderId

@Repository
interface OrderRepository : CrudRepository<Order, Long?> {
    fun findByOrderId(orderId: OrderId): Order?
}
