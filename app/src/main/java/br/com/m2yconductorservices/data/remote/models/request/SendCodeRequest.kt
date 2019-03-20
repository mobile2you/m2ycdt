package br.com.m2yconductorservices.data.remote.models.request

data class SendCodeRequest(
        val phone: String?,
        val cpf: String?
)