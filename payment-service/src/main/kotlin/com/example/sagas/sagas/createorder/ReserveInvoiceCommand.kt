package com.example.sagas.sagas.createorder

import com.example.sagas.domain.entity.OrderId
import io.eventuate.tram.commands.common.Command

class ReserveInvoiceCommand : Command {
    var orderId: OrderId

    constructor(orderId: OrderId) {
        this.orderId = orderId
    }
}
