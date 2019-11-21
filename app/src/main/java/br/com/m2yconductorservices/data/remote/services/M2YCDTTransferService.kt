package br.com.m2yconductorservices.data.remote.services

import br.com.m2yconductorservices.M2YCDTNetworkConstants
import br.com.m2yconductorservices.data.remote.models.request.*
import br.com.m2yconductorservices.data.remote.models.response.*
import io.reactivex.Single
import retrofit2.http.*

interface M2YCDTTransferService {

    @Deprecated("Fix TransferResponse bankCode use getTransfersFavored")
    @POST("${M2YCDTNetworkConstants.TRANSFER_URL}getTransfers")
    fun getTransfers() : Single<List<TransferResponse>>

    @POST("${M2YCDTNetworkConstants.TRANSFER_URL}getTransfers")
    fun getTransfersFavored() : Single<List<TransferResponseNew>>

    @POST("${M2YCDTNetworkConstants.TRANSFER_URL}favoriteTransfer")
    fun favoriteTransfer(@Body request: FavoriteTransferRequest) : Single<Any>

    @POST("${M2YCDTNetworkConstants.TRANSFER_URL}editFavoriteTransfer")
    fun editFavoriteTransfer(@Body request: EditFavoriteTransferRequest) : Single<Any>

    @POST("${M2YCDTNetworkConstants.TRANSFER_URL}deleteFavoriteTransfer")
    fun deleteFavoriteTransfer(@Body request: DeleteFavoriteTransferRequest) : Single<Any>

    @POST("${M2YCDTNetworkConstants.CDT_TRANSFER_URL}performBankTransfer")
    fun performBankTransfer(@Body transferRequest: TransferBankRequest): Single<TransferBankResponse>

    @POST("${M2YCDTNetworkConstants.CDT_TRANSFER_URL}getBankTransfers")
    fun getBankTransfers(@Body idAccount: AccountIdIntRequest?): Single<List<VoucherBankResponse>>

}