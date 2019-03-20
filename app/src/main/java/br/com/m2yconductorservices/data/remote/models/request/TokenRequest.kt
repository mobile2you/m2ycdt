package br.com.m2yconductorservices.data.remote.models.request

data class RegisterTokenRequest(
        var token: String?,
        var device: String?,
        var os: String?
)
