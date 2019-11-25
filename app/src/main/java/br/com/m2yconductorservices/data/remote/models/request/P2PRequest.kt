package br.com.m2yconductorservices.data.remote.models.request

data class P2PRequest (
        var name: String?,
        var cpf: String?,
        var account: String?,
        var nickname: String?
)

data class EditFavoriteP2PRequest (
        val id: String,
        var nickname: String?
)

data class DeleteFavoriteP2PRequest (
        val id: String
)