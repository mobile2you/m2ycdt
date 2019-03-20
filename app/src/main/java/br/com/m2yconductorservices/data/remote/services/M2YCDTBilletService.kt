package br.com.m2yconductorservices.data.remote.services

import br.com.m2yconductorservices.M2YCDTNetworkConstants
import br.com.m2yconductorservices.data.remote.models.request.TicketGenerateRequest
import br.com.m2yconductorservices.data.remote.models.response.TicketGenerateResponse
import br.com.m2yconductorservices.data.remote.models.response.TicketGeneratedResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface M2YCDTBilletService {

    @POST("${M2YCDTNetworkConstants.CDT_BILLET_URL}generateTicket")
    fun generateTicket(@Body request: TicketGenerateRequest) : Single<TicketGeneratedResponse>

    @POST("${M2YCDTNetworkConstants.CDT_BILLET_URL}getTickets")
    fun getTickets() : Single<List<TicketGenerateResponse>>
}