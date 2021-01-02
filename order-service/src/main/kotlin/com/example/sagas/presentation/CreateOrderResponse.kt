package com.example.sagas.presentation

import com.fasterxml.jackson.annotation.JsonProperty

data class CreateOrderResponse(
    @JsonProperty("order_id") var orderId: String,
    @JsonProperty("order_status") var orderStatus: String,
)
