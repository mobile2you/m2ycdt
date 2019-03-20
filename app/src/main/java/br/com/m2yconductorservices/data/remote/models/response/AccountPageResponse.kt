package br.com.m2yconductorservices.data.remote.models.response

data class AccountPageResponse(
    val content: List<AccountResponse>?
)

data class AccountResponse(
        var id: String?
)