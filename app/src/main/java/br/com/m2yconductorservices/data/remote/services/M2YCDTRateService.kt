package br.com.m2yconductorservices.data.remote.services

import br.com.m2yconductorservices.M2YCDTNetworkConstants
import br.com.m2yconductorservices.data.remote.models.request.RateRequest
import br.com.m2yconductorservices.data.remote.models.response.RateResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface M2YCDTRateService {

    @POST("${M2YCDTNetworkConstants.CDT_RATE_URL}carrierDetails")
    fun carrierDetails(@Body request: RateRequest): Single<RateResponse>
}