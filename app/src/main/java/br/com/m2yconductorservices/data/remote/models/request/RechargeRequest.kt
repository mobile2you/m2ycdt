package br.com.m2yconductorservices.data.remote.models.request

data class RechargeRequest(
        var accountId: Int,
        var amountKey: Int = 0,
        var ddd: String = "",
        var phoneNumber: String = "",
        var dealerCode: String = "",
        var dealerName: String = "",
        var amount: Float = 0f,
        var auth: String = "",
        var date: String = "",
        var orderId: String = "",
        var password: String = "",
        var cardId: String = "",
        var nickname: String = ""
)
