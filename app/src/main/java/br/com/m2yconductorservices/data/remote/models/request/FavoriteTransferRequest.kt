package br.com.m2yconductorservices.data.remote.models.request

data class FavoriteTransferRequest(
        var bank: String,
        var agency: String,
        var account: String,
        var digit: String,
        var name: String,
        var cpf: String,
        var nickname: String?
)