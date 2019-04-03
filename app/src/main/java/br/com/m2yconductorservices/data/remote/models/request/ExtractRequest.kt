package br.com.m2yconductorservices.data.remote.models.request

data class ExtractRequest(
        val id: String,
        val dataInicio: String?,
        val dataFim: String?,
        val page: Int?,
        val flagCredito: Boolean?
)