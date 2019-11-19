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

    @PUT("${M2YCDTNetworkConstants.TRANSFER_P2P_URL}editFavoriteP2p")
    fun editFavoriteP2p(@Body request: EditFavoriteP2PRequest): Single<P2PResponse>

    @DELETE("${M2YCDTNetworkConstants.TRANSFER_P2P_URL}deleteFavoriteP2p")
    fun deleteFavoriteP2p(@Body request: DeleteFavoriteP2PRequest): Single<Any>

    @POST("${M2YCDTNetworkConstants.TRANSFER_P2P_URL}getP2p")
    fun getP2p() : Single<List<P2PResponse>>

    @POST("${M2YCDTNetworkConstants.TRANSFER_P2P_URL}performP2PTransfer")
    fun performP2PTransfer(@Body request: TransferRequest): Single<TransferResellerResponse>

    @POST("${M2YCDTNetworkConstants.TRANSFER_P2P_URL}getp2pTransfer")
    fun getp2pTransfer(@Body idAccount: AccountIdIntRequest?): Single<List<Transferp2pResponse>>

}