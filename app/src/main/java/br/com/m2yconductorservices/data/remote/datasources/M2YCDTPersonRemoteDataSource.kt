package br.com.m2yconductorservices.data.remote.datasources

import br.com.m2yconductorservices.data.remote.M2YCDTServiceGenerator
import br.com.m2yconductorservices.data.remote.interceptors.M2YCDTInterceptor
import br.com.m2yconductorservices.data.remote.models.request.IdRequest
import br.com.m2yconductorservices.data.remote.models.request.PeopleIdRequest
import br.com.m2yconductorservices.data.remote.services.M2YCDTPersonService

object M2YCDTPersonRemoteDataSource {

    val service = M2YCDTServiceGenerator.createService(serviceClass = M2YCDTPersonService::class.java,
            interceptors = listOf(M2YCDTInterceptor()))

    fun findPerson(peopleId: IdRequest?) = service.findPerson(peopleId)

    fun getPeople(peopleId: PeopleIdRequest?) = service.getPeople(peopleId)
}