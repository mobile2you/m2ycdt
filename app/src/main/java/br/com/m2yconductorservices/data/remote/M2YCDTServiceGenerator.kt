package br.com.m2yconductorservices.data.remote

import br.com.m2yconductorservices.BuildConfig
import br.com.m2yconductorservices.M2YCDTNetworkConstants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by azul on 08/11/17.
 */
object M2YCDTServiceGenerator {

    fun <S> createService(serviceClass: Class<S>, interceptors: List<Interceptor>? = null): S {
        val retrofit = Retrofit.Builder()
            .baseUrl(M2YCDTNetworkConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(M2YCDTErrorHandlingCallAdapterFactory.create())

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(
            HttpLoggingInterceptor()
                .setLevel(if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE)
        )
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)

        interceptors?.let {
            for (interceptor in interceptors) {
                httpClient.addInterceptor(interceptor)
            }
        }
        retrofit.client(httpClient.build())
        return retrofit.build().create(serviceClass)
    }

}