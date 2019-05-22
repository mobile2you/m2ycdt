package br.com.m2yconductorservices.data.remote.services

import br.com.m2yconductorservices.M2YCDTNetworkConstants
import br.com.m2yconductorservices.data.remote.models.request.*
import br.com.m2yconductorservices.data.remote.models.response.UserResponse
import io.reactivex.Single
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

interface M2YCDTRegistrationService {
    @POST("${M2YCDTNetworkConstants.REGISTRATION_URL}sendSMS")
    fun sendSms(): Single<Any>

    @POST("${M2YCDTNetworkConstants.REGISTRATION_URL}signup")
    fun signup(@Body user: NewUserRequest): Single<Any>

    @POST("${M2YCDTNetworkConstants.REGISTRATION_URL}sendCode")
    fun sendCode(@Body user: RegisterSendCodeRequest): Single<Any>

    @Multipart
    @POST("${M2YCDTNetworkConstants.REGISTRATION_URL}sendSelfie")
    fun sendSelfie(@Part picture: MultipartBody.Part): Single<Any>

    @Multipart
    @POST("${M2YCDTNetworkConstants.REGISTRATION_URL}sendDocument")
    fun sendDocument(@Part picture: MultipartBody.Part? = null, @PartMap partMap: Map<String, @JvmSuppressWildcards RequestBody>): Single<Any>

    @POST("${M2YCDTNetworkConstants.REGISTRATION_URL}createAddress")
    fun createAddress(@Body address: CreateAddressRequest): Single<Any>

    @POST("${M2YCDTNetworkConstants.REGISTRATION_URL}setPassword")
    fun setPassword(@Body request: UpdatePasswordRequest): Single<Any>

    @POST("${M2YCDTNetworkConstants.REGISTRATION_URL}register")
    fun register(@Body request: RegisterRequest): Single<Any>

    @POST("${M2YCDTNetworkConstants.REGISTRATION_URL}checkCode")
    fun checkCode(@Body code: CheckCodeRequest?): Single<Any>

}