package com.example.sagas.application

import org.springframework.beans.factory.annotation.Autowired

class OrderCommandHandlers
    @Autowired
    private OrderService orderService

    fun commandHandler() : CommandHandlers {
        return SagaCommandHandlerBuilder
            .fromChannel("orderService")
            .onMessage(ApproveOrderCommand.class, this::approveOrder)
            .onMessage(RejectOrderCommand.class, this::rejectOrder)
            .build()
    }

    fun approveOrder() : String {
        return "Hello World!"
    }

    fun rejectOrder() : String {
        return "Hello World!"
    }
}
