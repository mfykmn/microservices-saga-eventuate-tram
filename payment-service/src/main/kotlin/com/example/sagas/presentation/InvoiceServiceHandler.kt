package com.example.sagas.presentation

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import com.example.sagas.domain.entity.PaymentId
import com.example.sagas.domain.repository.InvoiceRepository
import com.example.sagas.application.PaymentService

@RestController
@RequestMapping(path = ["api/payment/v1"])
class PaymentServiceHandler {

    @Autowired
    lateinit var paymentService: PaymentService

    @Autowired
    lateinit var invoiceRepository: InvoiceRepository

    @GetMapping(path = ["invoices/{payment_id}"])
    fun getInvoice(@PathVariable("payment_id") paymentId: PaymentId) : ResponseEntity<GetInvoiceResponse> {
        try {
            val invoice = invoiceRepository.findById(paymentId).get()
            if (invoice != null) {
                return ResponseEntity.ok(GetInvoiceResponse(
                    paymentId = invoice.paymentId,
                    orderId = invoice.orderId,
                    invoiceStatus = invoice.invoiceStatus.toString()))
            }
            return ResponseEntity.notFound().build()
        }catch(e: Exception){
            println(e)
            return ResponseEntity.status(500).build()
        }
    }

    @PostMapping(path = ["invoices"])
    fun createInvoice(@RequestBody request: CreateInvoiceRequest) : ResponseEntity<CreateInvoiceResponse> {
        // TODO: バリデーション

        val invoice = paymentService.createInvoice(request.orderId)

        return ResponseEntity.ok(CreateInvoiceResponse(paymentId = invoice.paymentId, invoiceStatus = invoice.invoiceStatus.toString()))
    }
}
