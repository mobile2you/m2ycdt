package br.com.m2yconductorservices.data.repositories

import br.com.m2yconductorservices.data.remote.datasources.M2YCDTPeerTransferRemoteDataSource
import br.com.m2yconductorservices.data.remote.models.request.P2PRequest

object M2YCDTPeerTransferRepository {

    fun favoriteP2p(request: P2PRequest) = M2YCDTPeerTransferRemoteDataSource.favoriteP2p(request)

    fun getP2p() = M2YCDTPeerTransferRemoteDataSource.getP2p()
}