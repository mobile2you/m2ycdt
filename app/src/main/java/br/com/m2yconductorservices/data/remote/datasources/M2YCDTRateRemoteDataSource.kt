package br.com.m2yconductorservices.data.remote.datasources

import br.com.m2yconductorservices.data.remote.M2YCDTServiceGenerator
import br.com.m2yconductorservices.data.remote.interceptors.M2YCDTInterceptor
import br.com.m2yconductorservices.data.remote.models.request.*
import br.com.m2yconductorservices.data.remote.services.M2YCDTCardService
import br.com.m2yconductorservices.data.remote.services.M2YCDTRateService

object M2YCDTRateRemoteDataSource {

    val service = M2YCDTServiceGenerator.createService(
        serviceClass = M2YCDTRateService::class.java,
        interceptors = listOf(M2YCDTInterceptor())
    )

    fun carrierDetails(request: RateRequest) = service.carrierDetails(request)
}