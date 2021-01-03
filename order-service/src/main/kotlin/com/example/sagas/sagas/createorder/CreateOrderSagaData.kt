package com.example.sagas.sagas.createorder

import com.example.sagas.domain.entity.Order

enum class RejectionReason {
    INSUFFICIENT_CREDIT, UNKNOWN_CUSTOMER
}

data class CreateOrderSagaData(
    val order: Order,
    var rejectionReason: RejectionReason? = null)
