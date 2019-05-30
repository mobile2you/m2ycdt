package br.com.m2yconductorservices.data.remote.models.request

import java.io.Serializable

data class TransportRequest (
    var cardNumber: Int?,
    var accountId: Int?,
    var creditType: Int?,
    var productCode: Int?,
    var value : Float?,
    var amount: Float,
    var cardId: Int?,
    var password: String?

): Serializable