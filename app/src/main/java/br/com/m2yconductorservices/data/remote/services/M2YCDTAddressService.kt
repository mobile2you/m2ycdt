package br.com.m2yconductorservices.data.remote.services

import br.com.m2yconductorservices.M2YCDTNetworkConstants
import br.com.m2yconductorservices.data.remote.models.request.CepRequest
import br.com.m2yconductorservices.data.remote.models.response.CepResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface M2YCDTAddressService {

    @POST("${M2YCDTNetworkConstants.CDT_ADDRESS_URL}checkZip")
    fun checkZip(@Body request: CepRequest): Single<CepResponse>
}