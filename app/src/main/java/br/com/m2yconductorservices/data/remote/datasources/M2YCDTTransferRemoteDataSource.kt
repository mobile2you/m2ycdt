package br.com.m2yconductorservices.data.remote.datasources

import br.com.m2yconductorservices.M2YCDTNetworkConstants
import br.com.m2yconductorservices.data.remote.M2YCDTServiceGenerator
import br.com.m2yconductorservices.data.remote.interceptors.M2YCDTInterceptor
import br.com.m2yconductorservices.data.remote.models.request.AccountIdIntRequest
import br.com.m2yconductorservices.data.remote.models.request.FavoriteTransferRequest
import br.com.m2yconductorservices.data.remote.models.request.TransferBankRequest
import br.com.m2yconductorservices.data.remote.models.request.TransferRequest
import br.com.m2yconductorservices.data.remote.services.M2YCDTTransferService

object M2YCDTTransferRemoteDataSource {

    val service = M2YCDTServiceGenerator.createService(serviceClass = M2YCDTTransferService::class.java,
            interceptors = listOf(M2YCDTInterceptor()))

    fun performBankTransfer(transferRequest: TransferBankRequest) = service.performBankTransfer(transferRequest)

    fun getBankTransfers(idAccount: AccountIdIntRequest?) = service.getBankTransfers(idAccount)

    fun getTransfers() = service.getTransfers()

    fun getTransfersFavored() = service.getTransfersFavored()

    fun favoriteTransfer(request: FavoriteTransferRequest) = service.favoriteTransfer(request)
    
    fun editFavoriteTransfer(request: EditFavoriteTransferRequest) = service.editFavoriteTransfer(request)

    fun deleteFavoriteTransfer(request: DeleteFavoriteTransferRequest) = service.deleteFavoriteTransfer(request)
}