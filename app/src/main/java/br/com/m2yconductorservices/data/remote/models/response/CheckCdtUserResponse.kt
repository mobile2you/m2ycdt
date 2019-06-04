package br.com.m2yconductorservices.data.remote.models.response

import java.io.Serializable

data class CheckCdtUserResponse (
    var hasPersonId: Boolean,
    var hasAccountId: Boolean,
    var hasCardId: Boolean

) : Serializable


data class AssociateCardResponse (
    var id: String?,
    var name: String?,
    var picture: String,
    var email: String?,
    var cpf: String?,
    var phone: String?

): Serializable