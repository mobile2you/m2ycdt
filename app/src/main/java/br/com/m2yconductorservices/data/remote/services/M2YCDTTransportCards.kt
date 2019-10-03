package br.com.m2yconductorservices.data.remote.services

import br.com.m2yconductorservices.M2YCDTNetworkConstants
import br.com.m2yconductorservices.data.remote.models.request.*
import br.com.m2yconductorservices.data.remote.models.response.TransportRechargeResponse
import br.com.m2yconductorservices.data.remote.models.response.TransportResponse
import br.com.m2yconductorservices.data.remote.models.response.UniqueTicketResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface M2YCDTTransportCards {

    @POST("${M2YCDTNetworkConstants.BANKS_TRANSPORTS}getProducts")
    fun getProducts(@Body request: TransportRequest) : Single<List<TransportResponse>>

    @POST("${M2YCDTNetworkConstants.BANKS_TRANSPORTS}recharge")
    fun recharge(@Body request: TransportRequest) : Single<TransportRechargeResponse>

    @POST("${M2YCDTNetworkConstants.BANKS_TRANSPORTS}getSptransCards")
    fun getSptransCards() : Single<List<TransportFavored>>

    @POST("${M2YCDTNetworkConstants.BANKS_TRANSPORTS}favoriteSptransCard")
    fun favoriteSptransCard(@Body request: TransportFavored) : Single<Any>

    @POST("${M2YCDTNetworkConstants.BANKS_TRANSPORTS}getRecharges")
    fun getRecharges(@Body request: AccountIdIntRequest) : Single<UniqueTicketResponse>

    @POST("${M2YCDTNetworkConstants.BANKS_TRANSPORTS}deleteCardFavorite")
    fun deleteCardFavorite(@Body request: DeleteTransportFavoriteRequest) : Single<Any>

    @POST("${M2YCDTNetworkConstants.BANKS_TRANSPORTS}editCardFavorite")
    fun editCardFavorite(@Body request: EditTransportFavoriteRequest) : Single<Any>

}