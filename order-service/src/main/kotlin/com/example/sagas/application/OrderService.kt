package com.example.sagas.application

import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional
import io.eventuate.tram.sagas.orchestration.SagaInstanceFactory
import com.example.sagas.domain.entity.Order
import com.example.sagas.domain.entity.ItemType
import com.example.sagas.sagas.createorder.CreateOrderSaga
import com.example.sagas.sagas.createorder.CreateOrderSagaData
import com.example.sagas.domain.repository.OrderRepository
import java.math.BigDecimal

@Service
class OrderService {
    @Autowired
    lateinit var orderRepository: OrderRepository

    @Autowired
    lateinit var sagaInstanceFactory: SagaInstanceFactory

    @Autowired
    lateinit var createOrderSaga: CreateOrderSaga

    @Transactional
    fun createOrder(itemType: ItemType, price: BigDecimal, currency: String) : Order {
        val order = Order(itemType, price, currency)
        val data = CreateOrderSagaData(order = order)
        sagaInstanceFactory.create(createOrderSaga, data)
        return orderRepository.findById(data.order.orderId).get()
    }
}
