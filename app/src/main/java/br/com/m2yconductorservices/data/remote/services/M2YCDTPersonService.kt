package br.com.m2yconductorservices.data.remote.services

import br.com.m2yconductorservices.M2YCDTNetworkConstants
import br.com.m2yconductorservices.data.remote.models.request.AccountIdIntRequest
import br.com.m2yconductorservices.data.remote.models.request.IdRequest
import br.com.m2yconductorservices.data.remote.models.request.PeopleIdRequest
import br.com.m2yconductorservices.data.remote.models.response.GetPeopleResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface M2YCDTPersonService {

    @POST("${M2YCDTNetworkConstants.CDT_PERSON_URL}findPerson")
    fun findPerson(@Body accountId: IdRequest?): Single<GetPeopleResponse>

    @POST("${M2YCDTNetworkConstants.PERSON_URL}getPerson")
    fun getPerson(@Body request: AccountIdIntRequest?): Single<GetPeopleResponse>

    @POST("${M2YCDTNetworkConstants.CDT_PERSON_URL}getPeople")
    fun getPeople(@Body peopleId: PeopleIdRequest?): Single<GetPeopleResponse>
}