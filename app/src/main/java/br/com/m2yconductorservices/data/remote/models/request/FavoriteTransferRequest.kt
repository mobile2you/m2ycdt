package br.com.m2yconductorservices.data.remote.models.request

data class FavoriteTransferRequest(
        var bank: String,
        var bankName: String,
        var agency: String,
        var account: String,
        var digit: String,
        var name: String,
        var cpf: String,
        var nickname: String,
        var accountType: String,
        var cpfOrCnpj: String
)

data class EditFavoriteTransferRequest (
        val id: String,
        var nickname: String
)

data class DeleteFavoriteTransferRequest(
        var id: String
)