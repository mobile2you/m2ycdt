package br.com.m2yconductorservices.data.remote.models.request

import java.io.Serializable

data class TransportFavored (
    var id: String?,
    var name: String?,
    var cardNumber: Long?,
    var color: String?

) : Serializable

data class DeleteTransportFavoriteRequest (
    val favorite_id: String
)

data class EditTransportFavoriteRequest (
    val favorite_id: String,
    val name: String
)