package br.com.m2yconductorservices.data.remote.services

import br.com.m2yconductorservices.M2YCDTNetworkConstants
import br.com.m2yconductorservices.data.remote.models.response.BankModel
import io.reactivex.Single
import retrofit2.http.POST

interface M2YCDTBankService {

    @POST("${M2YCDTNetworkConstants.BANKS_URL}getBanks")
    fun getBanks() : Single<List<BankModel>>
}