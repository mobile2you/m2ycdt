package br.com.m2yconductorservices.data.repositories

import br.com.m2yconductorservices.data.remote.datasources.M2YPersonRemoteDataSource
import br.com.m2yconductorservices.data.remote.models.request.PersonRequest

object M2YPersonRepository {
    fun getPerson(request: PersonRequest) = M2YPersonRemoteDataSource.getPerson(request)
}