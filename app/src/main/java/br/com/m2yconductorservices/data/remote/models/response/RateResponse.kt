package br.com.m2yconductorservices.data.remote.models.response

import br.com.m2yconductorservices.data.local.models.RateModel

data class RateResponse(
    val carrierId: Int,
    val rateValue: Float,
    val operation: OperationResponse,
    val leftoverExemption: Int
) {
    fun toModel() = RateModel(rateValue, leftoverExemption)
}

data class OperationResponse(
    val operationType: Int,
    val name: String,
    val description: String,
    val rateExemptionQuantity: Int,
    val exemptionPeriod: Int
)