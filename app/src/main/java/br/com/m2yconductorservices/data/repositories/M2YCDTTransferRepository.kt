package br.com.m2yconductorservices.data.repositories

import br.com.m2yconductorservices.data.local.models.*
import br.com.m2yconductorservices.data.remote.datasources.M2YCDTTransferRemoteDataSource
import br.com.m2yconductorservices.data.remote.models.request.AccountIdIntRequest
import br.com.m2yconductorservices.data.remote.models.request.DeleteFavoriteTransferRequest
import br.com.m2yconductorservices.data.remote.models.request.EditFavoriteTransferRequest
import br.com.m2yconductorservices.data.remote.models.request.FavoriteTransferRequest
import io.reactivex.Single

object M2YCDTTransferRepository {

    fun performBankTransfer(newTransfer: NewTransferModel): Single<ReceiptModel> {
        return M2YCDTTransferRemoteDataSource.performBankTransfer(
            newTransfer.toBankRequest(
                originalAccountId = try {
                    M2YCDTAccountRepository.accountId.toLong()
                } catch (e: Exception) {
                    0L
                }
            )
        ).map { it.toReceiptModel() }
    }

    fun getBankTransfers(idAccount: AccountIdIntRequest?) =
        M2YCDTTransferRemoteDataSource.getBankTransfers(idAccount)

    fun favoriteTransfer(request: FavoriteTransferRequest) =
        M2YCDTTransferRemoteDataSource.favoriteTransfer(request)

    fun editBankFavorite(request: EditFavoriteTransferRequest) =
        M2YCDTTransferRemoteDataSource.editBankFavorite(request)

    fun deleteBankFavorite(request: DeleteFavoriteTransferRequest) =
        M2YCDTTransferRemoteDataSource.deleteBankFavorite(request)

    fun getTransfers() = M2YCDTTransferRemoteDataSource.getTransfers()

    fun getTransfersFavored() = M2YCDTTransferRemoteDataSource.getTransfersFavored()
    
}