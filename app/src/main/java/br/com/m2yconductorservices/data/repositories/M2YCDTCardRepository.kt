package br.com.m2yconductorservices.data.repositories

import br.com.m2yconductorservices.data.remote.datasources.M2YCDTCardRemoteDataSource
import br.com.m2yconductorservices.data.remote.models.request.BlockAndUnBlockRequest
import br.com.m2yconductorservices.data.remote.models.request.UpdatePassCardRequest
import br.com.m2yconductorservices.data.remote.models.request.VerifyPasswordRequest

object M2YCDTCardRepository {

    fun verifyPassword(request: VerifyPasswordRequest) = M2YCDTCardRemoteDataSource.validateCard(request)

    fun unlockCard(card: BlockAndUnBlockRequest) = M2YCDTCardRemoteDataSource.unlockCard(card)

    fun blockCard(card: BlockAndUnBlockRequest) = M2YCDTCardRemoteDataSource.blockCard(card)

    fun updateCardPassword(request: UpdatePassCardRequest) = M2YCDTCardRemoteDataSource.updateCardPassword(request)
}