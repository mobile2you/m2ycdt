package br.com.m2yconductorservices.data.remote.services

import br.com.m2yconductorservices.M2YCDTNetworkConstants
import br.com.m2yconductorservices.data.remote.models.request.*
import br.com.m2yconductorservices.data.remote.models.response.*
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface M2YCDTLoginService {


    @POST("${M2YCDTNetworkConstants.LOGIN_URL}signin")
    fun signin(@Body request: LoginRequest): Single<UserResponse>

    @POST("${M2YCDTNetworkConstants.LOGIN_URL}forgotPass")
    fun forgotPassword(@Body cpf: CpfRequest): Single<ForgotPasswordResponse>

    @POST("${M2YCDTNetworkConstants.LOGIN_URL}resetPassword")
    fun resetPassword(@Body request: EmailRequest): Single<Any>

    @POST("${M2YCDTNetworkConstants.LOGIN_URL}checkCode")
    fun forgotPasswordCheckCode(@Body cpf: ForgotPassRequest): Single<Unit>

    @POST("${M2YCDTNetworkConstants.LOGIN_URL}forgotPass")
    fun forgotPasswordResendCode(@Body cpf: CpfRequest): Single<Unit>

    @POST("${M2YCDTNetworkConstants.LOGIN_URL}checkCpf")
    fun checkCpf(@Body cpf: CpfRequest?): Single<FirstAccessResponse>

    @POST("${M2YCDTNetworkConstants.LOGIN_URL}register")
    fun register(@Body request: LoginSignUpRequest): Single<UserResponse>

    @POST("${M2YCDTNetworkConstants.LOGIN_URL}checkCode")
    fun checkCode(@Body code: CheckCodeRequest?): Single<Any>

    @POST("${M2YCDTNetworkConstants.LOGIN_URL}sendCode")
    fun sendCode(@Body request: SendCodeRequest?): Single<Any>

    @POST("${M2YCDTNetworkConstants.LOGIN_URL}validateAccount")
    fun validateAccount(@Body request: ValidateAccountRequest): Single<ValidateAccountResponse>

    @POST("${M2YCDTNetworkConstants.LOGIN_URL}checkCdtUser")
    fun checkCdtUser(@Body cpf: CpfRequest?): Single<CheckCdtUserResponse>

    @POST("${M2YCDTNetworkConstants.LOGIN_URL}associateCard")
    fun associateCard(@Body request: AssociateCardRequest?): Single<AssociateCardResponse>

    @POST("${M2YCDTNetworkConstants.LOGIN_URL}createPerson")
    fun createPerson(@Body request: CreatePersonRequest?): Single<AssociateCardResponse>

}