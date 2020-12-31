package com.example.sagas.domain.repository

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import com.example.sagas.domain.entity.OrderId
import org.springframework.data.mongodb.repository.Query

@Repository
interface OrderRepository : MongoRepository<DbOrder, Long?> {
    fun save(order: DbOrder)

    @Query("{'order_id' : ?0}")
    fun findByOrderId(orderId: OrderId): DbOrder?
}
