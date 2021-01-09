package com.example.sagas.sagas.createorder

import com.example.sagas.domain.entity.OrderId
import io.eventuate.tram.commands.common.Command

data class ReserveInvoiceCommand(val orderId: OrderId) : Command
