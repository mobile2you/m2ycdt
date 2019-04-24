package br.com.m2yconductorservices.data.remote.models.response

class RechargeVoucherResponse(
        val orderId: String?,
        var transactionCode: String,
        var ddd: String,
        var phoneNumber: String,
        var dealerCode: String,
        var dealerName: String = "",
        var amount: String,
        val rechargeDate: String,
        val nickname: String = "") {

    val amountFloat: Float
        get() = amount.toFloat()
}