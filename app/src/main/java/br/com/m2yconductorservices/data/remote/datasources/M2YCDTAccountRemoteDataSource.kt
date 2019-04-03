package br.com.m2yconductorservices.data.remote.datasources

import android.os.Build
import br.com.m2yconductorservices.M2YCDTNetworkConstants
import br.com.m2yconductorservices.data.remote.M2YCDTServiceGenerator
import br.com.m2yconductorservices.data.remote.interceptors.M2YCDTInterceptor
import br.com.m2yconductorservices.data.remote.models.request.*
import br.com.m2yconductorservices.data.remote.services.M2YCDTAccountService
import io.reactivex.Single
import okhttp3.MultipartBody

object M2YCDTAccountRemoteDataSource {

    private val service = M2YCDTServiceGenerator.createService(serviceClass = M2YCDTAccountService::class.java,
            interceptors = listOf(M2YCDTInterceptor()))

    fun getAbout() = service.getAbout()

    fun getAccountIds() = service.getAccountIds()

    fun findAccount(accountId: IdRequest?) = service.findAccount(accountId)

    fun getCards(accountId: IdRequest?) = service.getCards(accountId)

    fun sendCode() = service.sendCode()

    fun updatePhone(newPhone: PhoneRequest) = service.updatePhone(newPhone)

    fun updatePassword(newPassword: String, confirmPassword: String) = service.updatePassword(UpdatePasswordRequest(newPassword, confirmPassword))

    fun updatePhoto(picture: MultipartBody.Part) = M2YCDTServiceGenerator.createService(serviceClass = M2YCDTAccountService::class.java,
            interceptors = listOf(M2YCDTInterceptor(false))).updatePhoto(picture)

    fun checkPassword(password: PassRequest) = service.checkPassoword(password)

    fun registerToken(token: String): Single<Any> {
        val specialCharRegex = Regex("[!@#\$%^&*(),.?\":{}|<>()]")
        val doubleSpacesRegex = Regex(" +")
        val model = Build.MODEL.replace(specialCharRegex, "").replace(doubleSpacesRegex, "-")
        return service.registerToken(RegisterTokenRequest(token, model, "Android-${Build.VERSION.SDK_INT}"))
    }

    fun getTermsAndPolitics() = service.getTermsAndPolitics()


    fun getExtract(request: ExtractRequest) = service.getExtract(request)
}