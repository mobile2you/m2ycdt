package br.com.m2yconductorservices.data.remote.models.request

data class EditRechargeFavoredContactRequest (
    val favorite_id: String,
    val ddd: String,
    val phone: String,
    val mobile_carrier: String,
    val nickname: String
)