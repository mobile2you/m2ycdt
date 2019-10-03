package br.com.m2yconductorservices.data.repositories

import br.com.m2yconductorservices.data.remote.datasources.M2YCDTTransportRemoteDataSource
import br.com.m2yconductorservices.data.remote.models.request.*

object M2YCDTTransportRepository {

    fun getProducts(request: TransportRequest) = M2YCDTTransportRemoteDataSource.getProducts(request)

    fun recharge(request: TransportRequest) = M2YCDTTransportRemoteDataSource.recharge(request)

    fun getSptransCards() = M2YCDTTransportRemoteDataSource.getSptransCards()

    fun favoriteSptransCard(request: TransportFavored) = M2YCDTTransportRemoteDataSource.favoriteSptransCard(request)

    fun getRecharges(request: AccountIdIntRequest) = M2YCDTTransportRemoteDataSource.getRecharges(request)

    fun deleteCardFavorite(request: DeleteTransportFavoriteRequest) = M2YCDTTransportRemoteDataSource.deleteCardFavorite(request)

    fun editCardFavorite(request: EditTransportFavoriteRequest) = M2YCDTTransportRemoteDataSource.editCardFavorite(request)

}