package br.com.m2yconductorservices.data.remote.datasources

import br.com.m2yconductorservices.data.remote.M2YCDTServiceGenerator
import br.com.m2yconductorservices.data.remote.interceptors.M2YCDTInterceptor
import br.com.m2yconductorservices.data.remote.models.request.*
import br.com.m2yconductorservices.data.remote.services.M2YCDTLoginService

object M2YCDTLoginRemoteDataSource {
    private val service = M2YCDTServiceGenerator.createService(serviceClass = M2YCDTLoginService::class.java,
            interceptors = listOf(M2YCDTInterceptor()))

    fun signin(request: LoginRequest) = service.signin(request)

    fun forgotPassword(cpf: CpfRequest) = service.forgotPassword(cpf)

    fun resetPassword(request: EmailRequest) = service.resetPassword(request)

    fun checkCpf(cpf: CpfRequest?) = service.checkCpf(cpf)

    fun register(request: LoginSignUpRequest) = service.register(request)

    fun checkCode(code: CheckCodeRequest?) = service.checkCode(code)

    fun sendCode(request: SendCodeRequest) = service.sendCode(request)

    fun forgotPasswordCheckCode(request: ForgotPassRequest) = service.forgotPasswordCheckCode(request)

    fun forgotPasswordResendCode(cpf: CpfRequest) = service.forgotPasswordResendCode(cpf)

    fun validateAccount(request: ValidateAccountRequest) = service.validateAccount(request)

}