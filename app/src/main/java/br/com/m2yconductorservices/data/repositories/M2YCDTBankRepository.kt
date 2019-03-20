package br.com.m2yconductorservices.data.repositories

import br.com.m2yconductorservices.data.remote.datasources.M2YCDTBankRemoteDataSource

object M2YCDTBankRepository {

    fun getBanks() = M2YCDTBankRemoteDataSource.getBanks()

}