package com.example.sagas.presentation

import com.example.sagas.domain.entity.Order
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import com.example.sagas.application.OrderServiceImpl
import com.example.sagas.domain.entity.OrderId
import java.util.concurrent.CompletableFuture

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
    fun createOrder() : CompletableFuture<String?>? {
        // TODO: リクエスト取得
        // TODO: バリデーション
        // TODO: リクエストボディをドメインモデルに落とし込み

        return serviceImpl.createOrder()
    }
}
