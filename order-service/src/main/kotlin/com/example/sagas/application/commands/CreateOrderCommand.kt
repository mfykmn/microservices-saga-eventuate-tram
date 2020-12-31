package com.example.sagas.application.commands

import java.math.BigDecimal
import org.axonframework.modelling.command.TargetAggregateIdentifier

class CreateOrderCommand(
    @TargetAggregateIdentifier val orderId: String,
    val itemType: String,
    val price: BigDecimal,
    val currency: String,
    val orderStatus: String
)
