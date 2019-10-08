package br.com.m2yconductorservices.data.remote.services

import br.com.m2yconductorservices.M2YCDTNetworkConstants
import br.com.m2yconductorservices.data.remote.models.request.TicketPaymentV1Request
import br.com.m2yconductorservices.data.remote.models.response.TicketPaymentV1Response
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface M2YCDTPaymentV1Service {

    @POST("${M2YCDTNetworkConstants.CDT_PAYMENT_URL}pay")
    fun pay(@Body request: TicketPaymentV1Request): Single<TicketPaymentV1Response>

}