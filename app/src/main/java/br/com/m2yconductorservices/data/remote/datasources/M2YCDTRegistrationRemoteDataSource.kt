package br.com.m2yconductorservices.data.remote.datasources

import br.com.m2yconductorservices.M2YCDTNetworkConstants
import br.com.m2yconductorservices.data.remote.M2YCDTServiceGenerator
import br.com.m2yconductorservices.data.remote.interceptors.M2YCDTInterceptor
import br.com.m2yconductorservices.data.remote.models.request.NewUserRequest
import br.com.m2yconductorservices.data.remote.services.M2YCDTRegistrationService

object M2YCDTRegistrationRemoteDataSource {
    private var mService = M2YCDTServiceGenerator.createService(
            interceptors = listOf(M2YCDTInterceptor()),
            serviceClass = M2YCDTRegistrationService::class.java)

    fun sendSms() = mService.sendSms()

    fun signup(user : NewUserRequest) = mService.signup(user)
}