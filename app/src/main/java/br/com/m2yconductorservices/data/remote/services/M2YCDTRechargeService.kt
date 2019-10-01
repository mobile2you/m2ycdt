package br.com.m2yconductorservices.data.remote.services

import br.com.m2yconductorservices.M2YCDTNetworkConstants
import br.com.m2yconductorservices.data.remote.models.request.*
import br.com.m2yconductorservices.data.remote.models.response.*
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface M2YCDTRechargeService {

    @POST("${M2YCDTNetworkConstants.RECHARGE_URL}getRecharges")
    fun getRecharges(): Single<List<RechargeResponse>>

    @POST("${M2YCDTNetworkConstants.RECHARGE_URL}favoriteRecharge")
    fun favoriteRecharge(@Body request: FavoriteRechargeRequest): Single<Any>

    @POST("${M2YCDTNetworkConstants.RECHARGE_URL}confirmRecharge")
    fun confirmRecharge(@Body request: RechargeRequest): Single<RechargeConfirmationResponse>

    @POST("${M2YCDTNetworkConstants.CDT_RECHARGE_URL}phoneDealersList")
    fun phoneDealersList(): Single<DealersResponse>

    @POST("${M2YCDTNetworkConstants.CDT_RECHARGE_URL}getRecharges")
    fun getRecharges(@Body accountId: AccountIdRequest): Single<List<RechargeVoucherResponse>>

    @POST("${M2YCDTNetworkConstants.CDT_RECHARGE_URL}createRechargeRequest")
    fun getAmounts(@Body request: RechargeRequest): Single<PhoneAmountResponse>

    @POST("${M2YCDTNetworkConstants.CDT_RECHARGE_URL}createRechargeRequest")
    fun recharge(@Body request: RechargeRequest): Single<PhoneAmountResponse>

    @POST("${M2YCDTNetworkConstants.RECHARGE_URL}deleteRechargeFavorite")
    fun deleteRechargeFavorite(@Body request: DeleteRechargeFavoredContactRequest): Single<Any>

    @POST("${M2YCDTNetworkConstants.RECHARGE_URL}editRechargeFavorite")
    fun editRechargeFavorite(@Body request: EditRechargeFavoredContactRequest): Single<Any>

}