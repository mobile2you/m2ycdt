package br.com.m2yconductorservices.data.remote.datasources

import br.com.m2yconductorservices.M2YCDTNetworkConstants
import br.com.m2yconductorservices.data.remote.M2YCDTServiceGenerator
import br.com.m2yconductorservices.data.remote.interceptors.M2YCDTInterceptor
import br.com.m2yconductorservices.data.remote.models.request.AccountIdIntRequest
import br.com.m2yconductorservices.data.remote.models.request.TicketPaymentRequest
import br.com.m2yconductorservices.data.remote.models.request.ValidateBarCodeRequest
import br.com.m2yconductorservices.data.remote.services.M2YCDTPaymentService

object M2YCDTPaymentRemoteDataSource {

    private val service = M2YCDTServiceGenerator.createService(serviceClass = M2YCDTPaymentService::class.java,
            interceptors = listOf(M2YCDTInterceptor()))

    fun getPayment(accountId: AccountIdIntRequest) = service.getPayment(accountId)

    fun pay(request: TicketPaymentRequest) = service.pay(request)

    fun validate(barcode : ValidateBarCodeRequest?) = service.validate(barcode)
}