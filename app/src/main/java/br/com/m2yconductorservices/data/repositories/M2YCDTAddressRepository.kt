package br.com.m2yconductorservices.data.repositories

import br.com.m2yconductorservices.data.remote.datasources.M2YCDTAddressRemoteDataSource
import br.com.m2yconductorservices.data.remote.models.request.CepRequest


object M2YCDTAddressRepository {

    fun checkZip(cep: String) = M2YCDTAddressRemoteDataSource.checkZip(CepRequest(cep))

}