package br.com.m2yconductorservices.data.remote.models.response

data class TransferResellerResponse(val id: Int?,
                                    val transactionCode: String?,
                                    val originalAccount: Int?,
                                    val destinationAccount: Int?,
                                    val amount: Float?,
                                    val transactionDate: String?,
                                    val description: String?)

data class TransferBankResponse(
        val idOriginAccount: Long,
        val identificator: Long,
        val value: Float,
        val bankName: String?,
        val beneficiary: FavoredResponse,
        val transactionCode: String,
        val date: String
)

data class FavoredResponse(
        val docIdCpfCnpjEinSSN: Long,
        val name: String,
        val bankId: Long,
        val bankName: String?,
        val agency: Long,
        val accountDigit: String,
        val accountType: String,
        val account: Long
) {
    val accountWithDigit: String
        get() = "$account-$accountDigit"

    val accountTypeName: String
        get() = when (accountType) {
            "cc" -> "Conta Corrente"
            "pp" -> "Conta Poupança"
            else -> ""
        }
}