package br.com.m2yconductorservices.data.remote.models.request

data class VerifyPasswordRequest (
     var password: String?,
     var cardId: String?
)