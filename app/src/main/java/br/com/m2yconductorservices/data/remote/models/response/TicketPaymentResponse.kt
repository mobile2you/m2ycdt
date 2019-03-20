package br.com.m2yconductorservices.data.remote.models.response

data class TicketPaymentResponse(
        val barCodeNumber: String,
        val dueDate: String,
        val transactionDate: String
)