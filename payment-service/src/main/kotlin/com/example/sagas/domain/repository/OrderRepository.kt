package com.example.sagas.domain.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import com.example.sagas.domain.entity.Invoice
import com.example.sagas.domain.entity.PaymentId

@Repository
interface InvoiceRepository : CrudRepository<Invoice, PaymentId>
