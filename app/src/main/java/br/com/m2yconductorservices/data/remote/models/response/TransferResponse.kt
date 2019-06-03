package br.com.m2yconductorservices.data.remote.models.response

data class TransferResponse(
    var id: String?,
    var bank: BankResponse?,
    var agency: String?,
    var account: String?,
    var name: String?,
    var cpf: String?,
    var nickname: String?,
    var accountType: String?,
    var digit: String?,
    var cpfOrCnpj: String?,
    var color: String?
)

data class BankResponse(
    var id: String?,
    var code: String?,
    var name: String?,
    var color: String?
) {
    fun toModel() = BankModel(id ?: "", name ?: "", color ?: "", code?.toInt() ?: 0)
}