package br.com.m2yconductorservices.data.remote.models.request

data class IdRequest(
        var id: String?
)

data class IdIntRequest(
        var id: Int?
)

data class AccountIdIntRequest(
        val accountId: Int?
)

data class AccountIdRequest(
        val accountId: String?
)

data class PeopleIdRequest(
        val peopleId: Int?
)