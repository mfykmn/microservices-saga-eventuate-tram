package com.example.sagas.application

import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired
import com.example.sagas.domain.entity.Order
import com.example.sagas.domain.entity.OrderId
import com.example.sagas.domain.repository.OrderRepository
import com.example.sagas.domain.entity.ItemType
import java.math.BigDecimal

@Service
class OrderServiceImpl : OrderService {

    @Autowired
    lateinit var orderRepository: OrderRepository

    override fun createOrder(itemType: ItemType, price: BigDecimal, currency: String) : Order {
        // Orderを作る
        val order = Order(itemType, price, currency)

//        ResultWithEvents<Order> orderAndEvents = Order.createOrder(orderDetails)
//        Order order = orderAndEvents.result
//
        // Orderをデータベースに永続化する
        orderRepository.save(order)
//
//        // ドメインイベントをパブリッシュする
//        eventPublisher.publish(Order.class, Long.toString(order.getId(), orderAndEvents.events);
//
//        // Sagaを作成する
//        CreateOrderSagaState data = new CreateOrderSagaState(order.getId(), orderDetails);
//        createOrderSagaManager.create(data, Order.class, order.getId());

        return order
    }

    override fun findById(orderId: OrderId) : Order? {
        return orderRepository.findByOrderId(orderId)
    }
}
