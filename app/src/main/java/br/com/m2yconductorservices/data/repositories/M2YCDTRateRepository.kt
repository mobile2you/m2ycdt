package br.com.m2yconductorservices.data.repositories

import br.com.m2yconductorservices.data.remote.datasources.M2YCDTRateRemoteDataSource
import br.com.m2yconductorservices.data.remote.models.request.RateRequest

object M2YCDTRateRepository {

    fun carrierDetails(request: RateRequest) = M2YCDTRateRemoteDataSource.carrierDetails(request).map { it.toModel() }
}