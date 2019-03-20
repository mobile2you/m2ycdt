package br.com.m2yconductorservices.data.local.models

import br.com.m2yconductorservices.data.remote.models.request.FavoredRequest
import br.com.m2yconductorservices.data.remote.models.request.TransferBankRequest
import br.com.m2yconductorservices.data.remote.models.request.TransferRequest
import br.com.m2yconductorservices.utils.M2YCDTCpf
import br.com.m2yconductorservices.utils.extensions.CustomString
import br.com.m2yconductorservices.utils.extensions.m2yCdtFillWithChars
import br.com.m2yconductorservices.utils.extensions.m2yCdtUnmask
import com.google.gson.Gson
import java.io.Serializable

data class NewTransferModel(
        var amount: Float = 0f,
        var destinationUserId: String = "",
        var destinationAccountId: Long = 0,
        var name: String = "",
        var cpf: String = "",
        var tax: Float = 0f,
        var description: String = "",
        var nickname: String = "",
        var bankCode: String = "",
        var bankAgency: String = "",
        var bankAgencyDigit: String = "",
        var bankAccount: String = "",
        var bankAccountDigit: String = "",
        var bankName: String? = null,
        var bankAccountType: String? = null,
        var saveInFav: Boolean = false,
        var isFromFavorites: Boolean = false,
        var password: String = "",
        var cardId: String = ""
) : Serializable {
    val isBankTransfer: Boolean
        get() = !bankCode.isNullOrEmpty()

    val agencyDisplay: String
        get() = bankAgency.m2yCdtFillWithChars(4, '0', CustomString.BEFORE)

    val accountTypeDisplay: String
        get() = if (bankAccountType == "cc")
            "Conta corrente"
        else
            "Conta poupança"

    val accountWithDigit: String
        get() = "${bankAccount.m2yCdtFillWithChars(5, '0', CustomString.BEFORE)}-$bankAccountDigit"

    val jsonObject: TransferJson?
        get() {
            return try {
                Gson().fromJson(description, TransferJson::class.java)
            } catch (ex: Exception) {
                null
            }
        }

    class TransferJson(
            var destinationUserId: String = "",
            var destinationAccountId: Long = 0,
            var tax: Float = 0f,
            var bankCode: String = "",
            var bankAgency: String = "",
            var bankAgencyDigit: String = "",
            var bankAccount: String = "",
            var bankAccountDigit: String = "",
            var bankName: String? = null,
            var bankAccountType: String? = null,
            var saveInFav: Boolean = false,
            var isFromFavorites: Boolean = false
    ) : Serializable

}

fun NewTransferModel.toRequest(originalAccountId: Long) = TransferRequest(amount, description, destinationAccountId, originalAccountId, password, cardId)

fun NewTransferModel.toBankRequest(originalAccountId: Long): TransferBankRequest {
    val isCpf = M2YCDTCpf.isValid(this.cpf)
    val favored = FavoredRequest(type = if (isCpf) "fisico" else "juridico",
            docIdCpfCnpjEinSSN = this.cpf.m2yCdtUnmask().toLong(), name = this.name,
            bankId = this.bankCode.toLong(), agency = this.bankAgency.toLong(), account = this.bankAccount.toLong(),
            accountType = bankAccountType
                    ?: "cc", agencyDigit = this.bankAgencyDigit, accountDigit = this.bankAccountDigit)

    return TransferBankRequest(idOriginAccount = originalAccountId, value = this.amount,
            beneficiary = favored, description = description, password = password, cardId = cardId)
}