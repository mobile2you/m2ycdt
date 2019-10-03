package br.com.m2yconductorservices.data.remote.datasources

import br.com.m2yconductorservices.data.remote.M2YCDTServiceGenerator
import br.com.m2yconductorservices.data.remote.interceptors.M2YCDTInterceptor
import br.com.m2yconductorservices.data.remote.models.request.AccountIdIntRequest
import br.com.m2yconductorservices.data.remote.models.request.EditTransportFavoriteRequest
import br.com.m2yconductorservices.data.remote.models.request.TransportFavored
import br.com.m2yconductorservices.data.remote.models.request.TransportRequest
import br.com.m2yconductorservices.data.remote.services.M2YCDTTransportCards

object M2YCDTTransportRemoteDataSource {

    val service = M2YCDTServiceGenerator.createService(serviceClass = M2YCDTTransportCards::class.java,
            interceptors = listOf(M2YCDTInterceptor()))

    fun getProducts(request: TransportRequest) = service.getProducts(request)

    fun recharge(request: TransportRequest) = service.recharge(request)

    fun getSptransCards() = service.getSptransCards()

    fun favoriteSptransCard(request: TransportFavored) = service.favoriteSptransCard(request)

    fun getRecharges(request: AccountIdIntRequest) = service.getRecharges(request)

    fun deleteCardFavorite(id: String) = service.deleteCardFavorite(id)

    fun editCardFavorite(request: EditTransportFavoriteRequest) = service.editCardFavorite(request)

}