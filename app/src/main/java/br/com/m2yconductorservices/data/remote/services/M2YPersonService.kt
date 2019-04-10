package br.com.m2yconductorservices.data.remote.services

import br.com.m2yconductorservices.M2YCDTConstants
import br.com.m2yconductorservices.M2YCDTNetworkConstants
import br.com.m2yconductorservices.data.remote.models.request.PersonRequest
import br.com.m2yconductorservices.data.remote.models.response.PersonResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface M2YPersonService {

    @POST("${M2YCDTNetworkConstants.PERSON_URL}getPerson")
    fun getPerson(@Body request: PersonRequest): Single<PersonResponse>
}