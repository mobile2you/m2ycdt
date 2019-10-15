package br.com.m2yconductorservices.data.remote.models.request

data class EditTransferFavoredContactRequest(
    var favorite_id: String?,
    var nickname: String? = ""
)