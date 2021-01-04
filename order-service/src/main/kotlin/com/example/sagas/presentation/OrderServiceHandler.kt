package com.example.sagas.presentation

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import com.example.sagas.application.OrderService
import com.example.sagas.domain.entity.ItemType
import com.example.sagas.domain.entity.OrderId
import com.example.sagas.domain.repository.OrderRepository

@RestController
@RequestMapping(path = ["api/order/v1"])
class OrderServiceHandler {
    @Autowired
    lateinit var orderService: OrderService

    @Autowired
    lateinit var orderRepository: OrderRepository

    @GetMapping(path = ["orders/{order_id}"])
    fun getOrder(@PathVariable("order_id") orderId: OrderId) : ResponseEntity<GetOrderResponse> {
        try {
            val order = orderRepository.findById(orderId).get()
            if (order != null) {
                return ResponseEntity.ok(GetOrderResponse(
                    orderId = order.orderId,
                    orderStatus = order.orderStatus.toString(),
                    itemType = order.itemType.toString(),
                    price = order.price,
                    currency = order.currency))
            }
            return ResponseEntity.notFound().build()
        }catch(e: Exception){
            println(e)
            return ResponseEntity.status(500).build()
        }
    }

    @PostMapping(path = ["orders"])
    fun createOrder(@RequestBody request: CreateOrderRequest) : ResponseEntity<CreateOrderResponse> {
        // TODO: バリデーション

        val order = orderService.createOrder(
            itemType = ItemType.valueOf(request.itemType),
            price = request.price,
            currency = request.currency)

        return ResponseEntity.ok(CreateOrderResponse(orderId = order.orderId, orderStatus = order.orderStatus.toString()))
    }
}
