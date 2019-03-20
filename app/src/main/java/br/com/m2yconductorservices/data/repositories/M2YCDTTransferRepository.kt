package br.com.m2yconductorservices.data.repositories

import br.com.m2yconductorservices.data.local.models.*
import br.com.m2yconductorservices.data.remote.datasources.M2YCDTTransferRemoteDataSource
import br.com.m2yconductorservices.data.remote.models.request.AccountIdIntRequest
import br.com.m2yconductorservices.data.remote.models.request.FavoriteTransferRequest
import io.reactivex.Single

object M2YCDTTransferRepository {


    fun performP2PTransfer(newTransfer: NewTransferModel): Single<ReceiptModel> {
        return M2YCDTTransferRemoteDataSource.performP2PTransfer(newTransfer.toRequest(M2YCDTAccountRepository.accountId.toLong())).map {
            it.toReceiptModel()
        }
    }

    fun performBankTransfer(newTransfer: NewTransferModel): Single<ReceiptModel> {
        return M2YCDTTransferRemoteDataSource.performBankTransfer(newTransfer.toBankRequest(M2YCDTAccountRepository.accountId.toLong())).map {
            it.toReceiptModel()
        }
    }

    fun getp2pTransfer(idAccount: AccountIdIntRequest?) = M2YCDTTransferRemoteDataSource.getp2pTransfer(idAccount)

    fun getBankTransfers(idAccount: AccountIdIntRequest?) = M2YCDTTransferRemoteDataSource.getBankTransfers(idAccount)

    fun favoriteTransfer(request: FavoriteTransferRequest) = M2YCDTTransferRemoteDataSource.favoriteTransfer(request)

    fun getTransfers() = M2YCDTTransferRemoteDataSource.getTransfers()


}