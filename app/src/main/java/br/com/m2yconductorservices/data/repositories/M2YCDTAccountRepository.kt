package br.com.m2yconductorservices.data.repositories

import br.com.m2yconductorservices.data.local.M2YCDTPersistUserInformation
import br.com.m2yconductorservices.data.local.M2YCDTPreferencesHelper
import br.com.m2yconductorservices.data.local.models.BalanceModel
import br.com.m2yconductorservices.data.remote.datasources.M2YCDTAccountRemoteDataSource
import br.com.m2yconductorservices.data.remote.models.request.*
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
        val accountId = M2YCDTPreferencesHelper.accountId
        if (it.content?.map { it.id }?.contains(accountId) == true && accountId != null) {
            M2YCDTPersistUserInformation.accountId(accountId)
        } else {
            it.content?.firstOrNull()?.id?.let { firstAccountId ->
                M2YCDTPersistUserInformation.accountId(firstAccountId)
            }
        }
    }

    @Deprecated(
        "Should not be used anymore, harder to do maintenance",
        replaceWith = ReplaceWith("getAccount")
    )
    fun findAccount(accountId: IdRequest?) = M2YCDTAccountRemoteDataSource.findAccount(accountId)

    fun getAccount(accountId: IdRequest) =
        M2YCDTAccountRemoteDataSource.findAccount(accountId).map { it.toAccountModel() }

    @Deprecated(
        "Should not be used anymore, harder to do maintenance",
        replaceWith = ReplaceWith("getCardList")
    )
    fun getCards(accountId: IdRequest?) = M2YCDTAccountRemoteDataSource.getCards(accountId)

    fun getCardList(accountId: IdRequest) =
        M2YCDTAccountRemoteDataSource.getCards(accountId).map {
            it.cards?.map { it.toModel() } ?: listOf()
        }

    fun createVirtualCard(accountId: AccountIdIntRequest) =
        M2YCDTAccountRemoteDataSource.createVirtualCard(accountId).map { it.toModel() }

    var lastBalance = BalanceModel(null)
    val balanceObservable: Observable<BalanceModel> =
        Observable.create<BalanceModel> {
            val observableEmitter = it
            findAccount(IdRequest(accountId)).map { it.toModel() }
                .m2yCdtSingleSubscribe(
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

    fun updatePassword(newPassword: String, confirmPassword: String) =
        M2YCDTAccountRemoteDataSource.updatePassword(newPassword, confirmPassword)
            .doOnSuccess {
                M2YCDTPersistUserInformation.password(newPassword)
            }

    fun checkPassowrd(password: String) =
        M2YCDTAccountRemoteDataSource.checkPassword(PassRequest(password))

    fun updatePhone(phone: String) = M2YCDTAccountRemoteDataSource.updatePhone(PhoneRequest(phone))
        .doOnSuccess {
            M2YCDTPersistUserInformation.phone(phone)
        }

    fun getAvailableMonths() = M2YCDTAccountRemoteDataSource.getAvailableMonths()

    fun updatePhoto(file: File): Single<UserResponse> {
        val filePart = MultipartBody.Part.createFormData(
            "picture", file.name,
            RequestBody.create(MediaType.parse("image/*"), file)
        )

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
            M2YCDTPersistUserInformation.resetUser()
            M2YCDTPreferencesHelper.clearSharedPref()
            lastBalance = BalanceModel(null)
        }
    }

    fun getAvailableMonths(accountId: String) =
        M2YCDTAccountRemoteDataSource.getAvailableMonths(accountId)


}
