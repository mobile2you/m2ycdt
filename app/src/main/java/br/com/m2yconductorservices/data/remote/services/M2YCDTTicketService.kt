package br.com.m2yconductorservices.data.remote.services

import br.com.m2yconductorservices.M2YCDTNetworkConstants
import br.com.m2yconductorservices.data.remote.models.response.TicketGeneratedResponse
import br.com.m2yconductorservices.data.remote.models.response.TokenCDTResponse
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface M2YCDTTicketService {

    @GET("${M2YCDTNetworkConstants.CDT_BILLET_URL}billet")
    fun generateTicket(@Query("idConta") accountId: String,
                       @Query("tipoBoleto") billetType: Int,
                       @Query("valor") amount: Float,
                       @Query("dataVencimento") expiryDate: String): Single<TicketGeneratedResponse>

    @POST("${M2YCDTNetworkConstants.CDT_BILLET_URL}boletos/{ID}/registrar")
    fun registerTicket(@Path("ID") ticketId: String): Single<Any>

    @GET("${M2YCDTNetworkConstants.CDT_BILLET_URL}boletos/{ID}/pdf")
    fun getTicketPdf(@Path("ID") ticketId: String,
                     @Header("Accept") acceptHeader: String): Single<Response<ResponseBody>>

    @GET("${M2YCDTNetworkConstants.CDT_BILLET_URL}boletos/{ID}")
    fun getTicketDetail(@Path("ID") ticketId: String) : Single<TicketGeneratedResponse>

}