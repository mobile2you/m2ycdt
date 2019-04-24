package br.com.m2yconductorservices.data.repositories

import br.com.m2yconductorservices.data.local.M2YCDTPersistUserInformation
import br.com.m2yconductorservices.data.local.M2YCDTPreferencesHelper
import br.com.m2yconductorservices.data.local.models.BalanceModel
import br.com.m2yconductorservices.data.remote.datasources.M2YCDTAccountRemoteDataSource
import br.com.m2yconductorservices.data.remote.models.request.ExtractRequest
import br.com.m2yconductorservices.data.remote.models.request.IdRequest
import br.com.m2yconductorservices.data.remote.models.request.PassRequest
import br.com.m2yconductorservices.data.remote.models.request.PhoneRequest
import br.com.m2yconductorservices.data.remote.models.response.ExtractContainerModel
import br.com.m2yconductorservices.data.remote.models.response.UserResponse
import br.com.m2yconductorservices.data.remote.models.response.toModel
import br.com.m2yconductorservices.utils.extensions.m2yCdtSingleSubscribe
import io.reactivex.Observable
import io.reactivex.Single
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

object M2YCDTAccountRepository {

    fun getAbout() = M2YCDTAccountRemoteDataSource.getAbout()

    fun getAccountIds() = M2YCDTAccountRemoteDataSource.getAccountIds().doOnSuccess {
        M2YCDTPersistUserInformation.userAccounts(it)
        it.content?.firstOrNull()?.id?.let { firstAccountId -> M2YCDTPersistUserInformation.accountId(firstAccountId) }
    }

    fun findAccount(accountId: IdRequest?) = M2YCDTAccountRemoteDataSource.findAccount(accountId)

    fun getCards(accountId: IdRequest?) = M2YCDTAccountRemoteDataSource.getCards(accountId)


    var lastBalance = BalanceModel(null)
    val balanceObservable: Observable<BalanceModel> =
            Observable.create<BalanceModel> {
                val observableEmitter = it
                M2YCDTAccountRepository.findAccount(IdRequest(M2YCDTAccountRepository.accountId)).map { it.toModel() }.m2yCdtSingleSubscribe(
                        onSuccess = {
                            lastBalance = it
                            lastBalance.isFromRemote = true
                            observableEmitter.onNext(lastBalance)
                        },
                        onError = {
                            lastBalance.isFromRemote = true
                            observableEmitter.onNext(BalanceModel(null))
                        }
                )
                observableEmitter.onNext(lastBalance)
            }

    val accountId
        get() = M2YCDTPersistUserInformation.accountId()

    val userPhoto
        get() = M2YCDTPersistUserInformation.picture()

    fun updatePassword(newPassword: String, confirmPassword: String) = M2YCDTAccountRemoteDataSource.updatePassword(newPassword, confirmPassword)
            .doOnSuccess {
                M2YCDTPersistUserInformation.password(newPassword)
            }

    fun checkPassowrd(password: String) = M2YCDTAccountRemoteDataSource.checkPassword(PassRequest(password))

    fun updatePhone(phone: String) = M2YCDTAccountRemoteDataSource.updatePhone(PhoneRequest(phone))
            .doOnSuccess{
                M2YCDTPersistUserInformation.phone(phone)
            }

    fun updatePhoto(file: File): Single<UserResponse> {
        val filePart = MultipartBody.Part.createFormData("picture", file.name,
                RequestBody.create(MediaType.parse("image/*"), file))

        return M2YCDTAccountRemoteDataSource.updatePhoto(filePart)
                .doOnSuccess {
                    M2YCDTPersistUserInformation.picture(it.picture ?: "")
                }
    }

    fun sendCode() = M2YCDTAccountRemoteDataSource.sendCode()

    fun registerToken(token: String) = M2YCDTAccountRemoteDataSource.registerToken(token)

    @Deprecated("Para fazer o logout usando a API chamar o logoutFromApi")
    fun logout() {
        M2YCDTPreferencesHelper.apply {
            basicAuth = null
            sessionCookie = null
            userFingerprint = false
        }
        M2YCDTPersistUserInformation.resetUser()
    }

    fun getTermsAndPolitics() = M2YCDTAccountRemoteDataSource.getTermsAndPolitics()

    fun getExtract(request: ExtractRequest) =
        M2YCDTAccountRemoteDataSource.getExtract(request)
            .map { ExtractContainerModel(it.content.map { it.toModel() }, it.last == true) }

    fun logoutFromApi(): Single<Any> {
        return M2YCDTAccountRemoteDataSource.logout().onErrorResumeNext {
            Single.just(Any())
        }.doFinally {
            M2YCDTPreferencesHelper.apply {
                basicAuth = null
                sessionCookie = null
                userFingerprint = false
            }
            M2YCDTPersistUserInformation.resetUser()
        }
    }

}
