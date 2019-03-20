package br.com.m2yconductorservices.data.remote.services

import br.com.m2yconductorservices.M2YCDTNetworkConstants
import br.com.m2yconductorservices.data.remote.models.request.NewUserRequest
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface M2YCDTRegistrationService {
    @POST("${M2YCDTNetworkConstants.REGISTRATION_URL}sendSMS")
    fun sendSms(): Single<Any>

    @POST("${M2YCDTNetworkConstants.REGISTRATION_URL}signup")
    fun signup(@Body user: NewUserRequest): Single<Any>

}