package br.com.m2yconductorservices.data.local.models

data class BalanceModel(
        val balance: Float?,
        var isFromRemote: Boolean = false
)