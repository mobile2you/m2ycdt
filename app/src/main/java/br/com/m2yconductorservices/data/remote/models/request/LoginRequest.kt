package br.com.m2yconductorservices.data.remote.models.request

import java.io.Serializable

data class LoginRequest(
    var cpf: String,
    var password: String
)

data class LoginSignUpRequestPF(
    var name: String?,
    var phone: String?,
    var email: String?,
    var cpf: String?,
    var birth_date: String?,
    var mother_name: String?
)


data class NewPasswordRequest(
    var password: String?,
    var name: String?,
    var email: String?,
    var cpf: String?,
    var phone: String?,
    var code: String?
) : Serializable