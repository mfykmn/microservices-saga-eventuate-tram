package com.example.sagas.application.commands

import org.axonframework.modelling.command.TargetAggregateIdentifier

class CreateInvoiceCommand(
    @TargetAggregateIdentifier val paymentId: String,
    val orderId: String
)
