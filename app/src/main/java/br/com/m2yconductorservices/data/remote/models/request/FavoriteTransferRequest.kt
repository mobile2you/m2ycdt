package br.com.m2yconductorservices.data.remote.models.request

data class FavoriteTransferRequest(
        var bankCode: String,
        var agency: String,
        var account: String,
        var digit: String,
        var name: String,
        var cpf: String,
        var nickname: String,
        var accountType: String,
        var cpfOrCnpj: String
)