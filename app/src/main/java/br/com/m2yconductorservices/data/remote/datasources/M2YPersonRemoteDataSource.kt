package br.com.m2yconductorservices.data.remote.datasources

import br.com.m2yconductorservices.data.remote.M2YCDTServiceGenerator
import br.com.m2yconductorservices.data.remote.interceptors.M2YCDTInterceptor
import br.com.m2yconductorservices.data.remote.models.request.PersonRequest
import br.com.m2yconductorservices.data.remote.services.M2YPersonService

object M2YPersonRemoteDataSource {
    private val service = M2YCDTServiceGenerator.createService(serviceClass = M2YPersonService::class.java,
        interceptors = listOf(M2YCDTInterceptor()))

    fun getPerson(request: PersonRequest) = service.getPerson(request)
}