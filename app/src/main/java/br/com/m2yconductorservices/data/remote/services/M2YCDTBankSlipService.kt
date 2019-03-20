package br.com.m2yconductorservices.data.remote.services

import br.com.m2yconductorservices.M2YCDTNetworkConstants
import br.com.m2yconductorservices.data.remote.models.request.IdIntRequest
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface M2YCDTBankSlipService {

    @POST("${M2YCDTNetworkConstants.CDT_BANK_SLIP_URL}getPDF")
    fun getPDF(@Body ticketId: IdIntRequest?, @Header("Accept") accept: String = "application/pdf") : Single<Response<ResponseBody>>
}