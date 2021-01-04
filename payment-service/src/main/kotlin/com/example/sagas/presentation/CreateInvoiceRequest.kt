package com.example.sagas.presentation

import com.fasterxml.jackson.annotation.JsonProperty

data class CreateInvoiceRequest(
    @JsonProperty("order_id") var orderId: String,
)
