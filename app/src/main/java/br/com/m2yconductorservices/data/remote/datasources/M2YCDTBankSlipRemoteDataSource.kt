package br.com.m2yconductorservices.data.remote.datasources

import br.com.m2yconductorservices.M2YCDTNetworkConstants
import br.com.m2yconductorservices.data.remote.M2YCDTServiceGenerator
import br.com.m2yconductorservices.data.remote.interceptors.M2YCDTInterceptor
import br.com.m2yconductorservices.data.remote.models.request.IdIntRequest
import br.com.m2yconductorservices.data.remote.services.M2YCDTBankSlipService

object M2YCDTBankSlipRemoteDataSource {
    val service = M2YCDTServiceGenerator.createService(serviceClass = M2YCDTBankSlipService::class.java,
            interceptors = listOf(M2YCDTInterceptor(false)))

    fun getPDF(ticketId: IdIntRequest?) = service.getPDF(ticketId)
}