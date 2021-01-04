package com.example.sagas.sagas.createorder

interface ReserveInvoiceResult

class InvoiceNotFound : ReserveInvoiceResult
class InvoiceReserved : ReserveInvoiceResult
class InvoiceLimitExceeded : ReserveInvoiceResult
