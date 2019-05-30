package br.com.m2yconductorservices.data.repositories

import br.com.m2yconductorservices.data.remote.datasources.M2YCDTTransportRemoteDataSource
import br.com.m2yconductorservices.data.remote.models.request.TransportFavored
import br.com.m2yconductorservices.data.remote.models.request.TransportRequest

object M2YCDTTransportRepository {

    fun getProducts(request: TransportRequest) = M2YCDTTransportRemoteDataSource.getProducts(request)

    fun recharge(request: TransportRequest) = M2YCDTTransportRemoteDataSource.recharge(request)

    fun getSptransCards() = M2YCDTTransportRemoteDataSource.getSptransCards()

    fun favoriteSptransCard(request: TransportFavored) = M2YCDTTransportRemoteDataSource.favoriteSptransCard(request)

}