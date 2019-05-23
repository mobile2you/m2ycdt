package br.com.m2yconductorservices.data.remote.datasources

import br.com.m2yconductorservices.data.remote.M2YCDTServiceGenerator
import br.com.m2yconductorservices.data.remote.interceptors.M2YCDTInterceptor
import br.com.m2yconductorservices.data.remote.models.request.CepRequest
import br.com.m2yconductorservices.data.remote.services.M2YCDTAddressService

object M2YCDTAddressRemoteDataSource {

    private val service = M2YCDTServiceGenerator.createService(serviceClass = M2YCDTAddressService::class.java,
            interceptors = listOf(M2YCDTInterceptor()))

    fun checkZip(request: CepRequest) = service.checkZip(request)


}