package com.example.sagas.presentation

import com.example.sagas.domain.entity.Order
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
    fun getOrder(@PathVariable("order_id") orderId: OrderId) : ResponseEntity<Order> {
        val order = serviceImpl.findById(orderId)
        if (order != null) {
            return ResponseEntity.ok(order)
        }
        return ResponseEntity.notFound().build()
    }

    @PostMapping()
    fun createOrder(@RequestBody request: CreateOrderRequest) : ResponseEntity<String> {
        // TODO: バリデーション

        serviceImpl.createOrder(
            itemType = ItemType.valueOf(request.itemType),
            price = request.price,
            currency = request.currency)

        return ResponseEntity.ok("success")
    }
}
