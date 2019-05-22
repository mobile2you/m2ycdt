package br.com.m2yconductorservices.data.remote.models.request

data class RegisterRequest (
    var name: String?,
    var email: String?,
    var mothers_name: String?,
    var rg: String?,
    var birth_date: String?,
    var cpf: String?
)