package br.com.m2yconductorservices.data.remote.datasources

import br.com.m2yconductorservices.M2YCDTNetworkConstants
import br.com.m2yconductorservices.data.remote.M2YCDTServiceGenerator
import br.com.m2yconductorservices.data.remote.interceptors.M2YCDTInterceptor
import br.com.m2yconductorservices.data.remote.models.request.TicketGenerateRequest
import br.com.m2yconductorservices.data.remote.services.M2YCDTBilletService

object M2YCDTBilletRemoteDataSource {

    val service = M2YCDTServiceGenerator.createService(serviceClass = M2YCDTBilletService::class.java,
            interceptors = listOf(M2YCDTInterceptor()))


    fun generateTicket(request: TicketGenerateRequest) = service.generateTicket(request)

    fun getTickets() = service.getTickets()
}