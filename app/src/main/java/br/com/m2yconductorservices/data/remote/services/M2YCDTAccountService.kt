package br.com.m2yconductorservices.data.remote.services

import br.com.m2yconductorservices.M2YCDTNetworkConstants
import br.com.m2yconductorservices.data.remote.models.request.*
import br.com.m2yconductorservices.data.remote.models.response.*
import io.reactivex.Single
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface M2YCDTAccountService {

    @POST("${M2YCDTNetworkConstants.CDT_ACCOUNT_URL}getTransactions")
    fun getExtract(@Body request: ExtractRequest): Single<PaginatedResponse<ExtractResponse>>

    @POST("${M2YCDTNetworkConstants.ACCOUNT_URL}getAbout")
    fun getAbout(): Single<GetAboutResponse>

    @POST("${M2YCDTNetworkConstants.ACCOUNT_URL}getAccountIds")
    fun getAccountIds(): Single<AccountPageResponse>

    @POST("${M2YCDTNetworkConstants.ACCOUNT_URL}findAccount")
    fun findAccount(@Body accountId: IdRequest?): Single<CardPageResponse>

    @POST("${M2YCDTNetworkConstants.ACCOUNT_URL}getCards")
    fun getCards(@Body accountId: IdRequest?): Single<CardPageResponse>

    @POST("${M2YCDTNetworkConstants.CDT_ACCOUNT_URL}createVirtualCard")
    fun createVirtualCard(@Body accountId: AccountIdIntRequest): Single<CardResponse>

    @POST("${M2YCDTNetworkConstants.ACCOUNT_URL}updatePassword")
    fun updatePassword(@Body request: UpdatePasswordRequest): Single<Any>

    @POST("${M2YCDTNetworkConstants.ACCOUNT_URL}checkPassword")
    fun checkPassoword(@Body password: PassRequest): Single<Any>

    @POST("${M2YCDTNetworkConstants.ACCOUNT_URL}updateAbout")
    fun updatePhone(@Body phone: PhoneRequest): Single<UserResponse>

    @Multipart
    @POST("${M2YCDTNetworkConstants.ACCOUNT_URL}updatePhoto")
    fun updatePhoto(@Part picture: MultipartBody.Part): Single<UserResponse>

    @POST("${M2YCDTNetworkConstants.ACCOUNT_URL}sendCode")
    fun sendCode(): Single<Any>

    @POST("${M2YCDTNetworkConstants.ACCOUNT_URL}getAvailableMonths")
    fun getAvailableMonths(): Single<MonthResponse>

    @POST("${M2YCDTNetworkConstants.ACCOUNT_URL}registerToken")
    fun registerToken(@Body registerTokenRequest: RegisterTokenRequest): Single<Any>

    @POST("${M2YCDTNetworkConstants.ACCOUNT_URL}getTermsAndPolitics")
    fun getTermsAndPolitics(): Single<FirstAccessTerms>

    @POST("${M2YCDTNetworkConstants.ACCOUNT_URL}logout")
    fun logout(): Single<Any>

    @POST("${M2YCDTNetworkConstants.ACCOUNT_URL}getAvailableMonths")
    fun getAvailableMonths(@Body availableMonthsRequest: AvailableMonthsRequest): Single<AvailableMonthsResponse>

}