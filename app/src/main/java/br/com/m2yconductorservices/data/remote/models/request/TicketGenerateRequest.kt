package br.com.m2yconductorservices.data.remote.models.request

data class TicketGenerateRequest(
        var accountId: String,
        var value: Float,
        var expirationDate: String,
        var name: String? = null,
        var cpf: String? = null,
        var type: Int? = null
)