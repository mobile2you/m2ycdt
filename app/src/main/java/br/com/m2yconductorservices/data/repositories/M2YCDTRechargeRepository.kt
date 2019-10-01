package br.com.m2yconductorservices.data.repositories

import br.com.m2yconductorservices.data.remote.datasources.M2YCDTRechargeRemoteDataSource
import br.com.m2yconductorservices.data.remote.models.request.*

object M2YCDTRechargeRepository {

    fun getRecharges() = M2YCDTRechargeRemoteDataSource.getRecharges()

    fun favoriteRecharge(request: FavoriteRechargeRequest) = M2YCDTRechargeRemoteDataSource.favoriteRecharge(request)

    fun confirmRecharge(request: RechargeRequest) = M2YCDTRechargeRemoteDataSource.confirmRecharge(request)

    fun phoneDealersList() = M2YCDTRechargeRemoteDataSource.phoneDealersList().map { it.dealers }

    fun getAmounts(request: RechargeRequest) = M2YCDTRechargeRemoteDataSource.getAmounts(request)

    fun recharge(request: RechargeRequest) = M2YCDTRechargeRemoteDataSource.recharge(request)

    fun getRecharges(accountId : AccountIdRequest) = M2YCDTRechargeRemoteDataSource.getRecharges(accountId)

    fun deleteRechargeFavorite(request: DeleteRechargeFavoredContactRequest) = M2YCDTRechargeRemoteDataSource.deleteRechargeFavorite(request)

    fun editRechargeFavorite(request: EditRechargeFavoredContactRequest) = M2YCDTRechargeRemoteDataSource.editRechargeFavorite(request)
}