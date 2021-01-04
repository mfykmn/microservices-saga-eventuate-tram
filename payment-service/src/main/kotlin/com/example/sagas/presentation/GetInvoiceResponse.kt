package com.example.sagas.presentation

import com.fasterxml.jackson.annotation.JsonProperty

data class GetInvoiceResponse(
    @JsonProperty("payment_id") var paymentId: String,
    @JsonProperty("order_id") var orderId: String,
    @JsonProperty("invoice_status") var invoiceStatus: String,
)
