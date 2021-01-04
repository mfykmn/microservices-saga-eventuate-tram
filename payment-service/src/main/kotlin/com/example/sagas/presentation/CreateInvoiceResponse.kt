package com.example.sagas.presentation

import com.fasterxml.jackson.annotation.JsonProperty

data class CreateInvoiceResponse(
    @JsonProperty("payment_id") var paymentId: String,
    @JsonProperty("Invoice_status") var invoiceStatus: String,
)
