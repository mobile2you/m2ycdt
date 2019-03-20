package br.com.m2yconductorservices.data.remote.datasources

import br.com.m2yconductorservices.M2YCDTNetworkConstants
import br.com.m2yconductorservices.data.remote.M2YCDTServiceGenerator
import br.com.m2yconductorservices.data.remote.interceptors.M2YCDTInterceptor
import br.com.m2yconductorservices.data.remote.models.request.BlockAndUnBlockRequest
import br.com.m2yconductorservices.data.remote.models.request.UpdatePassCardRequest
import br.com.m2yconductorservices.data.remote.models.request.VerifyPasswordRequest
import br.com.m2yconductorservices.data.remote.services.M2YCDTCardService

object M2YCDTCardRemoteDataSource {

    val service = M2YCDTServiceGenerator.createService(serviceClass = M2YCDTCardService::class.java,
            interceptors = listOf(M2YCDTInterceptor()))

    fun unlockCard(card : BlockAndUnBlockRequest) = service.unlockCard(card)

    fun blockCard(card: BlockAndUnBlockRequest) = service.blockCard(card)

    fun validateCard(request: VerifyPasswordRequest) = service.validateCard(request)

    fun updateCardPassword(request: UpdatePassCardRequest) = service.updateCardPassword(request)
}