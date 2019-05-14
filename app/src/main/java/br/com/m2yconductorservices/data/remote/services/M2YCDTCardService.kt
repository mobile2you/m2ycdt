package br.com.m2yconductorservices.data.remote.services

import br.com.m2yconductorservices.M2YCDTNetworkConstants
import br.com.m2yconductorservices.data.remote.models.request.*
import br.com.m2yconductorservices.data.remote.models.response.TicketGenerateResponse
import br.com.m2yconductorservices.data.remote.models.response.ValidateCardPasswordResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface M2YCDTCardService {

    @POST("${M2YCDTNetworkConstants.CDT_CARD_URL}passwordValidation")
    fun validateCard(@Body request: VerifyPasswordRequest): Single<ValidateCardPasswordResponse>

    @POST("${M2YCDTNetworkConstants.CDT_CARD_URL}updatePassword")
    fun updateCardPassword(@Body request: UpdatePassCardRequest): Single<Any>

    @POST("${M2YCDTNetworkConstants.CARD_URL}unlockCard")
    fun unlockCard(@Body card: BlockAndUnBlockRequest): Single<Any>

    @POST("${M2YCDTNetworkConstants.CARD_URL}blockCard")
    fun blockCard(@Body card: BlockAndUnBlockRequest): Single<Any>

    @POST("${M2YCDTNetworkConstants.CARD_URL}cancelCard")
    fun cancelCard(@Body card: CardIdRequest): Single<Any>

    @POST("${M2YCDTNetworkConstants.CARD_URL}activateCard")
    fun activateCard(@Body activateCardRequest: ActivateCardRequest): Single<Any>

    @POST("${M2YCDTNetworkConstants.CARD_URL}requestCard")
    fun requestCard(): Single<TicketGenerateResponse>
}