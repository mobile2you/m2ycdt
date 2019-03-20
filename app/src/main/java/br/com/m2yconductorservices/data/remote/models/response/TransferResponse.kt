package br.com.m2yconductorservices.data.remote.models.response

data class TransferResponse (
        var id: String?,
        var bank: String?,
        var agency: String?,
        var account: String?,
        var name: String?,
        var cpf: String?,
        var nickname: String?
)