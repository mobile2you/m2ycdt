package br.com.m2yconductorservices.data.remote.models.request

data class RegisterSendCodeRequest(
    var name: String?,
    var phone: String?,
    var email: String?,
    var cpf: String?
)