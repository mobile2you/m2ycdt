package br.com.m2yconductorservices.data.repositories

import br.com.m2yconductorservices.data.local.M2YCDTPersistUserInformation
import br.com.m2yconductorservices.data.local.M2YCDTPreferencesHelper
import br.com.m2yconductorservices.data.local.models.SavedLoginModel
import br.com.m2yconductorservices.data.remote.datasources.M2YCDTLoginRemoteDataSource
import br.com.m2yconductorservices.data.remote.models.request.*

object M2YCDTLoginRepository {

    fun signin(request: LoginRequest) = M2YCDTLoginRemoteDataSource.signin(request).doOnSuccess {
        if (M2YCDTPersistUserInformation.cpf() != request.cpf) {
            M2YCDTPreferencesHelper.userFingerprint = false
        }
        M2YCDTPersistUserInformation.setUser(it)
        M2YCDTPersistUserInformation.userLogin = request.cpf
        M2YCDTPersistUserInformation.password(request.password)
        M2YCDTPersistUserInformation.urls(it.urls)
        it.picture?.let { it1 -> M2YCDTPersistUserInformation.picture(it1) }
    }.doOnError {
        if (it.message?.contains("CPF") == true) {
            M2YCDTPersistUserInformation.userLogin("")
        }
    }


    fun checkCpf(cpf: CpfRequest?) = M2YCDTLoginRemoteDataSource.checkCpf(cpf)

    fun checkCdtUser(cpf: CpfRequest?) = M2YCDTLoginRemoteDataSource.checkCdtUser(cpf)

    fun associateCard(request: AssociateCardRequest) = M2YCDTLoginRemoteDataSource.associateCard(request)

    fun createPerson(request: CreatePersonRequest) = M2YCDTLoginRemoteDataSource.createPerson(request)

    fun registerPF(request: LoginSignUpRequest) = M2YCDTLoginRemoteDataSource.registerPF(request)

    fun registerPJ(request: LoginSignUpRequest) = M2YCDTLoginRemoteDataSource.registerPJ(request)

    fun checkCode(code: CheckCodeRequest) = M2YCDTLoginRemoteDataSource.checkCode(code)

    fun resendCode(request: SendCodeRequest) = M2YCDTLoginRemoteDataSource.sendCode(request)

    fun sendCode(request: SendCodeRequest) = M2YCDTLoginRemoteDataSource.sendCode(request)

    fun forgotPassword(cpf: CpfRequest) = M2YCDTLoginRemoteDataSource.forgotPassword(cpf)

    fun resetPassword(request: EmailRequest) = M2YCDTLoginRemoteDataSource.resetPassword(request)

    fun forgotPasswordCheckCode(request: ForgotPassRequest) = M2YCDTLoginRemoteDataSource.forgotPasswordCheckCode(request)

    fun forgotPasswordResendCode(cpf: CpfRequest) = M2YCDTLoginRemoteDataSource.forgotPasswordResendCode(cpf)

    fun getSavedLoginData(): SavedLoginModel {
        return SavedLoginModel(
                M2YCDTPersistUserInformation.name(),
                M2YCDTPersistUserInformation.cpf(),
                M2YCDTPersistUserInformation.password())
    }

    fun getUserAccountId() = M2YCDTPersistUserInformation.accountId()

    fun validateAccount(request: ValidateAccountRequest) = M2YCDTLoginRemoteDataSource.validateAccount(request)

}