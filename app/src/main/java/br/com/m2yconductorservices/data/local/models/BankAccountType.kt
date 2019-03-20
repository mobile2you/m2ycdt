package br.com.m2yconductorservices.data.local.models

enum class BankAccountType(val serverName: String, val displayName: String) {
    CC("cc", "Conta corrente"),
    PP("pp", "Conta poupança")
}