package br.com.m2yconductorservices.data.remote.services

import br.com.m2yconductorservices.M2YCDTNetworkConstants
import br.com.m2yconductorservices.data.remote.models.response.GetInvoicesResponse
import io.reactivex.Single
import retrofit2.http.POST

interface M2YCDTInvoicesService {

    @POST("${M2YCDTNetworkConstants.INVOICES}getInvoices")
    fun getInvoices(): Single<GetInvoicesResponse>
}