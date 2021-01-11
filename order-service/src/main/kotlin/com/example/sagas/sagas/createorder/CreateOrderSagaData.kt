package com.example.sagas.sagas.createorder

import com.example.sagas.domain.entity.Order
import com.example.sagas.domain.entity.RejectionReason
import com.fasterxml.jackson.annotation.JsonProperty

data class CreateOrderSagaData(
    @JsonProperty("order") var order: Order,
    @JsonProperty("rejectionReason") var rejectionReason: RejectionReason?,
)
