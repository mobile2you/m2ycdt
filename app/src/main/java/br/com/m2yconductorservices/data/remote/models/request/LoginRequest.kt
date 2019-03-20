package br.com.m2yconductorservices.data.remote.models.request

import java.io.Serializable

data class LoginRequest(
        var cpf: String,
        var password: String
)

data class LoginSignUpRequest(
        var password: String?,
        var name: String?,
        var email: String?,
        var cpf: String?,
        var phone: String?,
        var code: String?
) : Serializable