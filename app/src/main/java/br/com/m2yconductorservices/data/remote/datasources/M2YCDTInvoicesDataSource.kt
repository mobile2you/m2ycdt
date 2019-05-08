package br.com.m2yconductorservices.data.remote.datasources

import br.com.m2yconductorservices.data.remote.M2YCDTServiceGenerator
import br.com.m2yconductorservices.data.remote.interceptors.M2YCDTInterceptor
import br.com.m2yconductorservices.data.remote.services.M2YCDTInvoicesService

object M2YCDTInvoicesDataSource {
    val service = M2YCDTServiceGenerator.createService(serviceClass = M2YCDTInvoicesService::class.java,
        interceptors = listOf(M2YCDTInterceptor()))

    fun getInvoices() = service.getInvoices()

}