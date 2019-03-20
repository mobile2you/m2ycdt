package br.com.m2yconductorservices.data.remote.models.request

data class ValidateAccountRequest(
        var accountId: String,
        var code: String?
)