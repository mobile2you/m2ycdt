package br.com.m2yconductorservices.data.remote.datasources

import br.com.m2yconductorservices.data.remote.M2YCDTServiceGenerator
import br.com.m2yconductorservices.data.remote.interceptors.M2YCDTInterceptor
import br.com.m2yconductorservices.data.remote.models.request.*
import br.com.m2yconductorservices.data.remote.services.M2YCDTRechargeService

object M2YCDTRechargeRemoteDataSource {

    private var service = M2YCDTServiceGenerator.createService(
        interceptors = listOf(M2YCDTInterceptor()),
        serviceClass = M2YCDTRechargeService::class.java
    )

    fun phoneDealersList() = service.phoneDealersList()

    fun getAmounts(request: RechargeRequest) = service.getAmounts(request)

    fun recharge(request: RechargeRequest) = service.recharge(request)

    fun getRecharges(accountId: AccountIdRequest) = service.getRecharges(accountId)

    fun getRecharges() = service.getRecharges()

    fun favoriteRecharge(request: FavoriteRechargeRequest) = service.favoriteRecharge(request)

    fun confirmRecharge(request: RechargeRequest) = service.confirmRecharge(request)

    fun deleteRechargeFavorite(request: DeleteRechargeFavoredContactRequest) = service.deleteRechargeFavorite(request)

    fun editRechargeFavorite(request: EditRechargeFavoredContactRequest) = service.editRechargeFavorite(request)
}
