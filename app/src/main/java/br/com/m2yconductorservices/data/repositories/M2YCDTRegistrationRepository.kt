package br.com.m2yconductorservices.data.repositories

import br.com.m2yconductorservices.data.remote.datasources.M2YCDTRegistrationRemoteDataSource
import br.com.m2yconductorservices.data.remote.models.DocumentType
import br.com.m2yconductorservices.data.remote.models.request.*
import java.io.File

object M2YCDTRegistrationRepository {

    fun sendSms() = M2YCDTRegistrationRemoteDataSource.sendSms()

    fun signup(user: NewUserRequest) = M2YCDTRegistrationRemoteDataSource.signup(user)

    fun sendCode(user : RegisterSendCodeRequest) = M2YCDTRegistrationRemoteDataSource.sendCode(user)

    fun sendSelfie(picture: File) = M2YCDTRegistrationRemoteDataSource.sendSelfie(picture)

    fun sendDocument(file: File, documentType: DocumentType) = M2YCDTRegistrationRemoteDataSource.sendDocument(file, documentType)

    fun createAddress(address: CreateAddressRequest) = M2YCDTRegistrationRemoteDataSource.createAddress(address)

    fun setPassword(request: UpdatePasswordRequest) = M2YCDTRegistrationRemoteDataSource.setPassword(request)

    fun register(request: RegisterRequest) = M2YCDTRegistrationRemoteDataSource.register(request)

    fun checkCode(code: CheckCodeRequest?) = M2YCDTRegistrationRemoteDataSource.checkCode(code)

}