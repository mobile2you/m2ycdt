package br.com.m2yconductorservices.data.repositories

import br.com.m2yconductorservices.data.local.models.NewTransferModel
import br.com.m2yconductorservices.data.local.models.ReceiptModel
import br.com.m2yconductorservices.data.local.models.toReceiptModel
import br.com.m2yconductorservices.data.local.models.toRequest
import br.com.m2yconductorservices.data.remote.datasources.M2YCDTPeerTransferRemoteDataSource
import br.com.m2yconductorservices.data.remote.models.request.AccountIdIntRequest
import br.com.m2yconductorservices.data.remote.models.request.DeleteFavoriteP2PRequest
import br.com.m2yconductorservices.data.remote.models.request.EditFavoriteP2PRequest
import br.com.m2yconductorservices.data.remote.models.request.P2PRequest
import io.reactivex.Single

object M2YCDTPeerTransferRepository {

    fun favoriteP2p(request: P2PRequest) = M2YCDTPeerTransferRemoteDataSource.favoriteP2p(request)
        
    fun editFavoriteP2p(request: EditFavoriteP2PRequest) = M2YCDTPeerTransferRemoteDataSource.editFavoriteP2p(request)

    fun deleteFavoriteP2p(request: DeleteFavoriteP2PRequest) = M2YCDTPeerTransferRemoteDataSource.deleteFavoriteP2p(request)

    fun getP2p() = M2YCDTPeerTransferRemoteDataSource.getP2p()

    fun getp2pTransfer(idAccount: AccountIdIntRequest?) = M2YCDTPeerTransferRemoteDataSource.getp2pTransfer(idAccount)

    fun performP2PTransfer(newTransfer: NewTransferModel): Single<ReceiptModel> {
        return M2YCDTPeerTransferRemoteDataSource.performP2PTransfer(
            newTransfer.toRequest(
                originalAccountId = try {
                    M2YCDTAccountRepository.accountId.toLong()
                } catch (e: Exception) {
                    0L
                }
            )
        ).map {
            it.toReceiptModel()
        }
    }

}