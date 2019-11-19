package br.com.m2yconductorservices.data.remote.datasources

import br.com.m2yconductorservices.M2YCDTNetworkConstants
import br.com.m2yconductorservices.data.remote.M2YCDTServiceGenerator
import br.com.m2yconductorservices.data.remote.interceptors.M2YCDTInterceptor
import br.com.m2yconductorservices.data.remote.models.request.P2PRequest
import br.com.m2yconductorservices.data.remote.services.M2YCDTPeerTransferService

object M2YCDTPeerTransferRemoteDataSource {

    private val service = M2YCDTServiceGenerator.createService(serviceClass = M2YCDTPeerTransferService::class.java,
            interceptors = listOf(M2YCDTInterceptor()))

    fun favoriteP2p(request: P2PRequest) = service.favoriteP2p(request)

    fun editFavoriteP2p(request: EditFavoriteP2PRequest) = service.editFavoriteP2p(request)

    fun deleteFavoriteP2p(request: DeleteFavoriteP2PRequest) = service.deleteFavoriteP2p(request)

    fun performP2PTransfer(request: TransferRequest) = service.performP2PTransfer(request)
   
    fun getP2p() = service.getP2p()

    fun getp2pTransfer(idAccount: AccountIdIntRequest?) = service.getp2pTransfer(idAccount)

}