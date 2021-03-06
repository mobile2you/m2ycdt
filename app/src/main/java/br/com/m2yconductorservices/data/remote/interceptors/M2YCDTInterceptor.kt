package br.com.m2yconductorservices.data.remote.interceptors

import android.os.Handler
import android.os.Looper
import br.com.m2yconductorservices.data.M2YCDTEnvironment
import br.com.m2yconductorservices.data.local.M2YCDTPreferencesHelper
import br.com.m2yconductorservices.data.remote.M2YCDTEncryptHelper
import br.com.m2yconductorservices.data.remote.M2YCDTUserUnauthorizedBus
import br.com.m2yconductorservices.data.remote.models.response.DataModel
import com.google.gson.Gson
import okhttp3.*
import okio.Buffer
import java.io.IOException

private const val REQUEST_HEADER_COOKIE = "Cookie"
private const val REQUEST_HEADER_APPLICATION = "Application"
private const val REQUEST_HEADER_ENCRYPTION = "Oamolas"
private const val RESPONSE_HEADER_COOKIE = "Set-Cookie"
private const val UNAUTHORIZED_CODE = 401
private const val MEDIA_TYPE = "application/json; charset=utf-8"

class M2YCDTInterceptor(
    encrypt: Boolean = true,
    private val encryptRequest: Boolean = encrypt,
    private val decryptResponse: Boolean = encrypt
) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = buildNewRequest(chain.request())
        val response = chain.proceed(request)
        checkUnnauthorizedHttpCode(response)
        saveCookieIfAny(response)
        return createNewResponseWithBody(response)
    }

    private fun createNewResponseWithBody(response: Response): Response {
        if (!encryptRequest) return response
        val responseBody = response.body()?.string()
        return try {
            val dataObject = Gson().fromJson(responseBody, DataModel::class.java)
            val newResponseBody =
                ResponseBody.create(MediaType.parse(MEDIA_TYPE), M2YCDTEncryptHelper.decrypt(dataObject.data))
            response.newBuilder().body(newResponseBody).build()
        } catch (e: Exception) {
            val newResponseBody =
                if (responseBody != null) ResponseBody.create(MediaType.parse(MEDIA_TYPE), responseBody) else null
            response.newBuilder().body(newResponseBody).build()
        }
    }

    private fun buildNewRequest(original: Request): Request {

        val body = createNewBody(original)

        val builder = original.newBuilder()
            .addHeader(REQUEST_HEADER_APPLICATION, M2YCDTEnvironment.applicationHeader)
            .addHeader(REQUEST_HEADER_ENCRYPTION, REQUEST_HEADER_ENCRYPTION)
            .method(original.method(), body)

        //Only adds if there's a cookie
        val cookie = M2YCDTPreferencesHelper.sessionCookie
        if (!cookie.isNullOrBlank()) {
            builder.addHeader(REQUEST_HEADER_COOKIE, cookie)
        }

        return builder.build()
    }

    private fun createNewBody(original: Request): RequestBody? {
        if (!decryptResponse) return original.body()
        val buffer = Buffer()
        original.body()?.writeTo(buffer)
        val newBody = buffer.readUtf8()
        return RequestBody.create(
            MediaType.parse(MEDIA_TYPE),
            Gson().toJson(DataModel(M2YCDTEncryptHelper.encrypt(newBody)))
        )
    }

    private fun checkUnnauthorizedHttpCode(response: Response) {
        if (response.code() == UNAUTHORIZED_CODE) {
            Handler(Looper.getMainLooper()).post { M2YCDTUserUnauthorizedBus.setEvent(Any()) }
        }
    }

    private fun saveCookieIfAny(response: Response) {
        val cookie = response.headers().get(RESPONSE_HEADER_COOKIE)
        if (!cookie.isNullOrBlank() && M2YCDTPreferencesHelper.sessionCookie != cookie) {
            M2YCDTPreferencesHelper.sessionCookie = cookie
        }
    }

}