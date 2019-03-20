package br.com.m2yconductorservices.data.remote.models.request

data class UpdatePasswordRequest(
        val password: String,
        val password_confirmation: String
)

data class UpdatePassCardRequest(
        val password: String?,
        val cardId: String?
)

