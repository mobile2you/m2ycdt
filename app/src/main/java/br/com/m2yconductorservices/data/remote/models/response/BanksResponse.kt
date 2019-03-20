package br.com.m2yconductorservices.data.remote.models.response

data class BanksResponse(
        val favorites: List<BankModel>,
        val general: List<BankModel>
)