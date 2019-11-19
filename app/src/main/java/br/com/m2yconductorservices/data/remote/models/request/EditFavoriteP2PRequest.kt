package br.com.m2yconductorservices.data.remote.models.request

data class EditFavoriteP2PRequest (
    val id: String,
    var name: String?,
    var cpf: String?,
    var account: String?,
    var nickname: String?
)