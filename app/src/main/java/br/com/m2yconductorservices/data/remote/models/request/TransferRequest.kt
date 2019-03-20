package br.com.m2yconductorservices.data.remote.models.request

data class TransferRequest(val amount: Float,
                           val description: String?,
                           val destinationAccount: Long,
                           val originalAccount: Long,
                           val password: String?,
                           val cardId: String?)

data class TransferBankRequest(
        val idOriginAccount: Long,
        val identificator: Long = 123,
        val subIssuerCode: String = "123",
        val description: String? = null,
        val value: Float,
        val beneficiary: FavoredRequest,
        val password: String?,
        val cardId: String?
)

data class FavoredRequest(
        val type: String,
        val docIdCpfCnpjEinSSN: Long,
        val name: String,
        val bankId: Long,
        val agency: Long,
        val account: Long,
        val accountType: String,
        val agencyDigit: String = "",
        val accountDigit: String = ""
)