package br.com.m2yconductorservices.data.remote.models.request

import br.com.m2yconductorservices.data.local.M2YCDTPersistUserInformation

sealed class RateRequest(val operationType: Int, val accountId: String = M2YCDTPersistUserInformation.accountId()) {
    class P2P : RateRequest(1)
    class PAYMENT : RateRequest(2)
    class BANK_TRANSFER : RateRequest(3)
    class TICKET : RateRequest(4)
    class PHONE_RECHARGE : RateRequest(5)
    class SPTRANS_RECHARGE : RateRequest(6)
    class TICKET_PAID : RateRequest(7)
}