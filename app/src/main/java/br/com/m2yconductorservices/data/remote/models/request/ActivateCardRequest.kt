package br.com.m2yconductorservices.data.remote.models.request

class ActivateCardRequest(
    val cardId: Int,
    val cardNumber: String,
    val cpf: String,
    val password: String
)