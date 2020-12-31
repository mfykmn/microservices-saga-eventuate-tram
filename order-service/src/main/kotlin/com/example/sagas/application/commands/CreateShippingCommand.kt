package com.example.sagas.application.commands

import org.axonframework.modelling.command.TargetAggregateIdentifier

class CreateShippingCommand(
    @TargetAggregateIdentifier val shippingId: String,
    val orderId: String,
    val paymentId: String
)
