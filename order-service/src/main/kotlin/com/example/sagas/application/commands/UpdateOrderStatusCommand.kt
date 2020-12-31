package com.example.sagas.application.commands

import org.axonframework.modelling.command.TargetAggregateIdentifier

class UpdateOrderStatusCommand(
    @TargetAggregateIdentifier val orderId: String,
    val orderStatus: String
)
