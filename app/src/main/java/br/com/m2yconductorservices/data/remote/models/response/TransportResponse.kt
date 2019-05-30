package br.com.m2yconductorservices.data.remote.models.response

import java.io.Serializable

class TransportResponse (
    var maxValue: Float?,
    var minValue: Float?,
    var creditType: Int?,
    var productCode: Int?,
    var description: String?,
    var unitaryValue: Float?

) : Serializable