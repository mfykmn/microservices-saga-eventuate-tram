package com.example.sagas.presentation

import java.math.BigDecimal
import com.fasterxml.jackson.annotation.JsonProperty

data class CreateOrderRequest(
    @JsonProperty("item_type") var itemType: String,
    @JsonProperty("price") var price: BigDecimal,
    @JsonProperty("currency") var currency: String,
)
