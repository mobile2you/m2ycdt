package br.com.m2yconductorservices.data.remote.models.request

sealed class RateRequest(val serverTypeId: Int) {
    class P2P : RateRequest(1)
    class PAYMENT : RateRequest(2)
    class BANK_TRANSFER : RateRequest(3)
    class TICKET : RateRequest(4)
    class RECHARGE : RateRequest(9)
}