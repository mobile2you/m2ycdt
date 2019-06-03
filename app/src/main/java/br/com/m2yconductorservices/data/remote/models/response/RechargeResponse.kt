package br.com.m2yconductorservices.data.remote.models.response

import java.io.Serializable

data class RechargeResponse(
        var id: String?,
        var phone: String?,
        var mobile_carrier: String?,
        var nickname: String?,
        var dealer: String?,
        var ddd: String?,
        var color: String?
) : Serializable