package br.com.m2yconductorservices.data.remote.models.request

data class ExtractRequest(
        val id: String,
        val beginDate: String?,
        val endDate: String?,
        val page: Int?,
        val isCredit: Boolean?
)