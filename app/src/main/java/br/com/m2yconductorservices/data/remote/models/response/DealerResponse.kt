package br.com.m2yconductorservices.data.remote.models.response

data class DealerResponse(
        val name: String,
        val code: String
)

data class DealersResponse(
        val dealers: List<DealerResponse>
)