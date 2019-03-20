package br.com.m2yconductorservices.data.remote.datasources

import br.com.m2yconductorservices.M2YCDTNetworkConstants
import br.com.m2yconductorservices.data.remote.M2YCDTServiceGenerator
import br.com.m2yconductorservices.data.remote.interceptors.M2YCDTInterceptor
import br.com.m2yconductorservices.data.remote.services.M2YCDTBankService

object M2YCDTBankRemoteDataSource {

    val service = M2YCDTServiceGenerator.createService(serviceClass = M2YCDTBankService::class.java,
            interceptors = listOf(M2YCDTInterceptor()))

    fun getBanks() = service.getBanks()

}