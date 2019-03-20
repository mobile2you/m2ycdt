package br.com.m2yconductorservices.data.remote.models.request

data class ForgotPassRequest (
        val cpf: String?,
        val code: String?
)