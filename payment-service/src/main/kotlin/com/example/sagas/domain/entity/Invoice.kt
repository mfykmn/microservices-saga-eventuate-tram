package com.example.sagas.domain.entity

import java.math.BigDecimal
import java.util.*
import javax.persistence.Entity
import javax.persistence.Table
import javax.persistence.Id
import javax.persistence.Column
import javax.persistence.Enumerated
import javax.persistence.EnumType

typealias PaymentId = String
typealias OrderId = String

enum class InvoiceStatus {
    PAID, PAYMENT_REVERSED
}

@Entity
@Table(name="invoices")
class Invoice(
    @Id
    @Column(name="payment_id")
    val paymentId: PaymentId,

    @Column(name="order_id")
    var orderId: OrderId,

    @Column(name="invoice_status")
    @Enumerated(EnumType.STRING)
    var invoiceStatus: InvoiceStatus) {

    constructor(_orderId: OrderId) : this(
        UUID.randomUUID().toString(),
        _orderId,
        InvoiceStatus.PAID)

    fun reverse() {
        this.invoiceStatus = InvoiceStatus.PAYMENT_REVERSED
    }
}
