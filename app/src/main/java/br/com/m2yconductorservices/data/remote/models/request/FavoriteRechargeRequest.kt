package br.com.m2yconductorservices.data.remote.models.request

data class FavoriteRechargeRequest(
        var ddd: String?,
        var phone: String?,
        var mobile_carrier: String?,
        var nickname: String?
)

data class EditRechargeFavoredContactRequest (
        val id: String,
        var nickname: String?
)

data class DeleteRechargeFavoredContactRequest(
        val id: String
)