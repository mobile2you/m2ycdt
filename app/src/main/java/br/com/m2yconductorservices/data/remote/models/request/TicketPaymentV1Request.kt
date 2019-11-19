package br.com.m2yconductorservices.data.remote.models.request

import br.com.m2yconductorservices.M2YCDTConstants
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

data class TicketPaymentV1Request(val idAccount: Long,
                                  val description: String?,
                                  val barCodeNumber: String,
                                  var dueDate: String,
                                  val assignor: String?,
                                  val assignorDocument: String?,
                                  val discount: Float? = null,
                                  val interest: Float? = null,
                                  val fine: Float? = null,
                                  val taxValue: Float? = 0f,
                                  val value: Float,
                                  var password: String?,
                                  var cardId: String?)

fun PaymentModel.toPaymentV1Request(idAccount: Long, name: String): TicketPaymentV1Request {
    return TicketPaymentV1Request(
        idAccount = idAccount,
        description =  M2YCDTConstants.PAYMENT_DESCRIPTION,
        barCodeNumber = barcode ?: "",
        dueDate = "${dueDate}T00:00:00.000Z",
        assignor = name,
        assignorDocument = document,
        discount = discount,
        interest = interest,
        fine = fine,
        value = value ?: 0f + (fine ?: 0f) + (interest ?: 0f),
        password =  password,
        cardId = cardId
    )
}