package br.com.m2yconductorservices.data.repositories

import br.com.m2yconductorservices.data.remote.datasources.M2YCDTBilletRemoteDataSource
import br.com.m2yconductorservices.data.remote.models.request.TicketGenerateRequest

object M2YCDTBilletRepository {

    fun generateTicket(request: TicketGenerateRequest) = M2YCDTBilletRemoteDataSource.generateTicket(request)

    fun getTickets() = M2YCDTBilletRemoteDataSource.getTickets()
}