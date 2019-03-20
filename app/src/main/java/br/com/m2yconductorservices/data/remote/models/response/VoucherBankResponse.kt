package br.com.m2yconductorservices.data.remote.models.response

import java.io.Serializable

data class VoucherBankResponse(
        var id: Int?,
        var idAdjustment: Float?,
        var transactionCode: String?,
        var idIssuer: Float?,
        var description: String?,
        var idOriginAccount: Float?,
        var value: Float?,
        var typeAccountFavored: String?,
        var nameFavored: String?,
        var bankFavored: Float?,
        var agencyFavored: Float?,
        var digitAgencyFavored: String?,
        var accountFavored: Float?,
        var digitAccountFavored: String?,
        var cnabFileName: String?,
        var statusTransfer: String?,
        var trariffCode: String?,
        var transferenceDate: String?,
        var cpfCnpjFavored: String?,
        var uid: String?,
        val bankName: String?
) : Serializable {

    val transferBankName: String
        get() = if (description?.contains("@@") == true)
            description?.split("@@")?.first() ?: ""
        else
            ""
}