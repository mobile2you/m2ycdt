package br.com.m2yconductorservices.data.local.models

import br.com.m2yconductorservices.data.remote.models.request.TicketPaymentRequest
import java.io.Serializable

data class TicketModel(
        val type: TicketType,
        val id: String? = null,
        val barcode: String? = null,
        var amount: Float = 0f,
        val url: String? = null,
        var date: String? = null,
        var sourceAccount: String? = null,
        var destination: String? = null,
        var document: String? = null,
        var discount: Float? = 0f,
        var interest: Float? = 0f,
        var fine: Float? = 0f,
        var charges: Float? = 0f,
        var dueDate: String? = null

        ) : Serializable

enum class TicketType {
    INVOICE, ADD_CREDIT, PAYMENT
}