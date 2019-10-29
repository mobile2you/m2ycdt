package br.com.m2yconductorservices.data.remote.models.response

import java.io.Serializable

data class P2PResponse (
        var name: String?,
        var cpf: String?,
        var account: String?,
        var nickname: String?,
        var color: String?,
        var id: String?
) : Serializable