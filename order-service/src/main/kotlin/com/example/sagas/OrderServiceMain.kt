package com.example.sagas

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class OrderServiceMain

fun main(args: Array<String>) {
    runApplication<OrderServiceMain>(*args)
}
