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

    fun getP2p() = service.getP2p()

    fun deleteP2pFavorite(request: EditTransferFavoredContactRequest) = service.deleteP2pFavorite(request)

    fun editP2pFavorite(request: EditTransferFavoredContactRequest) = service.editP2pFavorite(request)
}