package br.com.m2yconductorservices.data.remote.models.request

data class EditRechargeFavoredContactRequest (
    val id: String,
    var ddd: String?,
    var phone: String?,
    var mobile_carrier: String?,
    var nickname: String?
)