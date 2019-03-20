package br.com.m2yconductorservices.data.remote.models.request

data class TicketGenerateRequest(
        var accountId: String,
        var value: Float,
        var expirationDate: String
)