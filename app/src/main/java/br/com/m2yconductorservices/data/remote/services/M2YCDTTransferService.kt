package br.com.m2yconductorservices.data.remote.services

import br.com.m2yconductorservices.M2YCDTNetworkConstants
import br.com.m2yconductorservices.data.remote.models.request.AccountIdIntRequest
import br.com.m2yconductorservices.data.remote.models.request.FavoriteTransferRequest
import br.com.m2yconductorservices.data.remote.models.request.TransferBankRequest
import br.com.m2yconductorservices.data.remote.models.request.TransferRequest
import br.com.m2yconductorservices.data.remote.models.response.*
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface M2YCDTTransferService {

    @POST("${M2YCDTNetworkConstants.TRANSFER_URL}getTransfers")
    fun getTransfers() : Single<List<TransferResponse>>

    @POST("${M2YCDTNetworkConstants.TRANSFER_URL}favoriteTransfer")
    fun favoriteTransfer(@Body request: FavoriteTransferRequest) : Single<Any>

    @POST("${M2YCDTNetworkConstants.CDT_TRANSFER_URL}performBankTransfer")
    fun performBankTransfer(@Body transferRequest: TransferBankRequest): Single<TransferBankResponse>

    @POST("${M2YCDTNetworkConstants.CDT_TRANSFER_URL}performP2PTransfer")
    fun performP2PTransfer(@Body request: TransferRequest): Single<TransferResellerResponse>

    @POST("${M2YCDTNetworkConstants.CDT_TRANSFER_URL}getp2pTransfer")
    fun getp2pTransfer(@Body idAccount: AccountIdIntRequest?): Single<List<Transferp2pResponse>>

    @POST("${M2YCDTNetworkConstants.CDT_TRANSFER_URL}getBankTransfers")
    fun getBankTransfers(@Body idAccount: AccountIdIntRequest?): Single<List<VoucherBankResponse>>

}