package br.com.m2yconductorservices.data.remote.services

import br.com.m2yconductorservices.M2YCDTNetworkConstants
import br.com.m2yconductorservices.data.remote.models.request.BlockAndUnBlockRequest
import br.com.m2yconductorservices.data.remote.models.request.UpdatePassCardRequest
import br.com.m2yconductorservices.data.remote.models.request.VerifyPasswordRequest
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
}