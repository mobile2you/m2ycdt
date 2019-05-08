package br.com.m2yconductorservices.data.local.models

import java.io.Serializable

data class AccountModel(
    val balance: Float,
    val cards: List<CardModel>
) : Serializable