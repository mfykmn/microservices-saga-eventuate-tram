package com.example.sagas.application.events

class OrderShippedEvent constructor(
    val shippingId: String,
    val orderId: String,
    val paymentId: String
)
