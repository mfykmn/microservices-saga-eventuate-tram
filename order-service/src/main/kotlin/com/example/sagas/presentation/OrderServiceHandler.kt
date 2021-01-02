package com.example.sagas.presentation

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import com.example.sagas.application.OrderServiceImpl
import com.example.sagas.domain.entity.ItemType
import com.example.sagas.domain.entity.OrderId

@RestController
@RequestMapping(path = ["orders"])
class OrderServiceHandler {

    @Autowired
    lateinit var serviceImpl: OrderServiceImpl

    @GetMapping(path = ["{order_id}"])
    fun getOrder(@PathVariable("order_id") orderId: OrderId) : ResponseEntity<GetOrderResponse> {
        val order = serviceImpl.findById(orderId)
        if (order != null) {
            return ResponseEntity.ok(GetOrderResponse(
                orderId = order.orderId,
                orderStatus = order.orderStatus,
                itemType = order.itemType,
                price = order.price,
                currency = order.currency))
        }
        return ResponseEntity.notFound().build()
    }

    @PostMapping()
    fun createOrder(@RequestBody request: CreateOrderRequest) : ResponseEntity<CreateOrderResponse> {
        // TODO: バリデーション

        val order = serviceImpl.createOrder(
            itemType = ItemType.valueOf(request.itemType),
            price = request.price,
            currency = request.currency)

        return ResponseEntity.ok(CreateOrderResponse(orderId = order.orderId, orderStatus = order.orderStatus))
    }
}
