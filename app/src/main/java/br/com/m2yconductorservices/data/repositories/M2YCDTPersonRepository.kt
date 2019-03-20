package br.com.m2yconductorservices.data.repositories

import br.com.m2yconductorservices.data.remote.datasources.M2YCDTPersonRemoteDataSource
import br.com.m2yconductorservices.data.remote.models.request.IdRequest
import br.com.m2yconductorservices.data.remote.models.request.PeopleIdRequest

object M2YCDTPersonRepository {

    fun findPerson(peopleId: IdRequest?) = M2YCDTPersonRemoteDataSource.findPerson(peopleId)

    fun getPeople(peopleId: PeopleIdRequest?) = M2YCDTPersonRemoteDataSource.getPeople(peopleId)
}