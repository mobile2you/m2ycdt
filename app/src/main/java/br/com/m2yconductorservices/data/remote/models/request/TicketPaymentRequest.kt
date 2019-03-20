package br.com.m2yconductorservices.data.remote.models.request

import br.com.m2yconductorservices.data.local.models.PaymentModel

data class TicketPaymentRequest(val accountId: Long,
                                val description: String?,
                                val barCodeNumber: String,
                                var dueDate: String,
                                val assignor: String?,
                                val discount: Float? = null,
                                val taxValue: Float? = null,
                                val value: Float,
                                var password: String?,
                                var cardId: String?)

fun PaymentModel.toPaymentRequest(accountId: Long, name: String): TicketPaymentRequest {
    return TicketPaymentRequest(accountId,
            description,
            barcode ?: "",
            "${dueDate}T00:00:00.000Z",
            name,
            discount,
            charges,
            valueWithCharges ?: 0f,
            password,
            cardId
            )
}