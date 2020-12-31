package com.example.sagas.application

import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired
import com.example.sagas.domain.entity.Order
import com.example.sagas.domain.entity.OrderId
import com.example.sagas.domain.repository.DbOrder
import com.example.sagas.domain.repository.OrderRepository
import com.example.sagas.domain.entity.ItemType
import com.example.sagas.domain.entity.OrderStatus
import com.example.sagas.application.commands.CreateOrderCommand
import java.math.BigDecimal
import org.axonframework.commandhandling.gateway.CommandGateway
import java.util.UUID
import java.util.concurrent.CompletableFuture

@Service
class OrderServiceImpl : OrderService {

    @Autowired
    lateinit var commandGateway: CommandGateway

    @Autowired
    lateinit var orderRepository: OrderRepository

    override fun createOrder() : CompletableFuture<String?>? {
        // Orderを作る
        val order = Order(UUID.randomUUID().toString(),
            ItemType.HEADPHONE,
            BigDecimal(1000),
            "test",
            OrderStatus.CREATED)
//        ResultWithEvents<Order> orderAndEvents = Order.createOrder(orderDetails)
//        Order order = orderAndEvents.result
//
        // Orderをデータベースに永続化する
        orderRepository.save(DbOrder(orderId = order.orderId, itemId = 1, itemName = "test"))
//
//        // ドメインイベントをパブリッシュする
//        eventPublisher.publish(Order.class, Long.toString(order.getId(), orderAndEvents.events);
//
//        // Sagaを作成する
//        CreateOrderSagaState data = new CreateOrderSagaState(order.getId(), orderDetails);
//        createOrderSagaManager.create(data, Order.class, order.getId());

        return commandGateway.send(CreateOrderCommand(
            order.orderId,
            order.itemType.toString(),
            order.price,
            order.currency,
            order.orderStatus.toString()))
    }

    override fun findById(orderId: OrderId) : Order? {
        val dbOrder = orderRepository.findByOrderId(orderId)
        if (dbOrder != null) {
            return Order(dbOrder.orderId,
                ItemType.HEADPHONE,
                BigDecimal(1000),
                "test",
                OrderStatus.CREATED)
        }
        return null
    }
}
