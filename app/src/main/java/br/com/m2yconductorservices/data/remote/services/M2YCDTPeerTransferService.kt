package br.com.m2yconductorservices.data.remote.services

import br.com.m2yconductorservices.M2YCDTNetworkConstants
import br.com.m2yconductorservices.data.remote.models.request.P2PRequest
import br.com.m2yconductorservices.data.remote.models.response.P2PResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface M2YCDTPeerTransferService {

    @POST("${M2YCDTNetworkConstants.TRANSFER_P2P_URL}favoriteP2p")
    fun favoriteP2p(@Body request: P2PRequest): Single<P2PResponse>

    @POST("${M2YCDTNetworkConstants.TRANSFER_P2P_URL}getP2p")
    fun getP2p() : Single<List<P2PResponse>>

    @POST("${M2YCDTNetworkConstants.TRANSFER_P2P_URL}deleteP2pFavorite")
    fun deleteP2pFavorite(@Body request: EditTransferFavoredContactRequest): Single<Any>

    @POST("${M2YCDTNetworkConstants.TRANSFER_P2P_URL}editP2pFavorite")
    fun editP2pFavorite(@Body request: EditTransferFavoredContactRequest): Single<Any>
}