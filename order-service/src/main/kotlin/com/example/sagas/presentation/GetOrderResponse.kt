package com.example.sagas.presentation

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

data class GetOrderResponse(
    @JsonProperty("order_id") var orderId: String,
    @JsonProperty("order_status") var orderStatus: String,
    @JsonProperty("item_type") var itemType: String,
    @JsonProperty("price") var price: BigDecimal,
    @JsonProperty("currency") var currency: String,
)
