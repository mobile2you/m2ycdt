package br.com.m2yconductorservices.data.remote.datasources

import br.com.m2yconductorservices.data.remote.M2YCDTServiceGenerator
import br.com.m2yconductorservices.data.remote.interceptors.M2YCDTInterceptor
import br.com.m2yconductorservices.data.remote.models.DocumentType
import br.com.m2yconductorservices.data.remote.models.request.*
import br.com.m2yconductorservices.data.remote.services.M2YCDTRegistrationService
import io.reactivex.Single
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

object M2YCDTRegistrationRemoteDataSource {
    private var mService = M2YCDTServiceGenerator.createService(
            interceptors = listOf(M2YCDTInterceptor()),
            serviceClass = M2YCDTRegistrationService::class.java)

    private var serviceNotEncrypted = M2YCDTServiceGenerator.createService(
        serviceClass = M2YCDTRegistrationService::class.java,
        interceptors = listOf(M2YCDTInterceptor(false)))

    fun sendSms() = mService.sendSms()

    fun signup(user : NewUserRequest) = mService.signup(user)

    fun sendCode(user : RegisterSendCodeRequest) = mService.sendCode(user)

    fun sendSelfie(file: File): Single<Any> {
        val filePart = MultipartBody.Part.createFormData("picture", file.name,
            RequestBody.create(MediaType.parse("image/*"), file))

        return serviceNotEncrypted.sendSelfie(filePart)
    }

    fun sendDocument(file: File, documentType: DocumentType): Single<Any> {
        val filePart: MultipartBody.Part = MultipartBody.Part.createFormData("document", file.name, RequestBody.create(
            MediaType.parse("image/*"), file))
        return serviceNotEncrypted.sendDocument(filePart, documentType.toMap())
    }

    fun createAddress(address: CreateAddressRequest) = mService.createAddress(address)

    fun setPassword(request: UpdatePasswordRequest) = mService.setPassword(request)

    fun register(request: RegisterRequest) = mService.register(request)

    fun checkCode(code: CheckCodeRequest?) = mService.checkCode(code)



}