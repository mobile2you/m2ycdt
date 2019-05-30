package br.com.m2yconductorservices.data.remote.models.response

import java.io.Serializable

data class TransportRechargeResponse(
    var productCode: Int?,
    var orderNumber: Int?,
    var status: String?,
    var value: Float?,
    var amount: Float?,
    var date: String?
): Serializable