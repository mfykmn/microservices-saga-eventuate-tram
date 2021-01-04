package com.example.sagas.application

import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional
import com.example.sagas.domain.entity.Invoice
import com.example.sagas.domain.entity.OrderId
import com.example.sagas.domain.repository.InvoiceRepository

@Service
class PaymentService {
    @Autowired
    lateinit var invoiceRepository: InvoiceRepository

    @Transactional
    fun reserveInvoice(orderId: OrderId) : Invoice {
        val invoice = Invoice(orderId)
        return invoiceRepository.save(invoice)
    }
}
