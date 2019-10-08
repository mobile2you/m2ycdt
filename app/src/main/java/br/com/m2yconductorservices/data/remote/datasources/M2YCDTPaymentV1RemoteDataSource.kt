package br.com.m2yconductorservices.data.remote.datasources

import br.com.m2yconductorservices.data.remote.M2YCDTServiceGenerator
import br.com.m2yconductorservices.data.remote.interceptors.M2YCDTInterceptor
import br.com.m2yconductorservices.data.remote.models.request.TicketPaymentV1Request
import br.com.m2yconductorservices.data.remote.services.M2YCDTPaymentV1Service

object M2YCDTPaymentV1RemoteDataSource {

    private val service = M2YCDTServiceGenerator.createService(
        serviceClass = M2YCDTPaymentV1Service::class.java,
        interceptors = listOf(M2YCDTInterceptor())
    )

    fun pay(request: TicketPaymentV1Request) = service.pay(request)

}