package br.com.m2yconductorservices.data.repositories

import br.com.m2yconductorservices.data.remote.datasources.M2YCDTRegistrationRemoteDataSource
import br.com.m2yconductorservices.data.remote.models.request.NewUserRequest

object M2YCDTRegistrationRepository {
    fun sendSms() = M2YCDTRegistrationRemoteDataSource.sendSms()

    fun signup(user: NewUserRequest) = M2YCDTRegistrationRemoteDataSource.signup(user)
}