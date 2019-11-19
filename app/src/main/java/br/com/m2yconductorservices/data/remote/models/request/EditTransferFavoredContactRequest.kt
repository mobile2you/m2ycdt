package br.com.m2yconductorservices.data.remote.models.request

import java.io.Serializable

data class EditTransferFavoredContactRequest(
    var favorite_id: String?,
    var nickname: String? = ""
) : Serializable