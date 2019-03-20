package br.com.m2yconductorservices.data.remote.models.request

data class NewUserRequest(
        var name: String?,
        var phone: String?,
        var email: String?,
        var cpf: String?,
        var person_id: String?,
        var password: String
)