package br.com.m2yconductorservices.data.local.models

import java.io.Serializable
import java.util.*

data class CardModel(
    val id: String,
    val createDate: Date,
    val expireDate: Date,
    val isVirtual: Boolean,
    val cardNumber: String,
    val status: Int,
    val cvv: String
) :Serializable


fun Iterable<CardModel>.firstCard(): CardModel {
    return sortedBy { it.createDate.time }.first()
}

fun Iterable<CardModel>.firstCardOrNull(): CardModel? {
    return sortedBy { it.createDate.time }.firstOrNull()
}

fun Iterable<CardModel>.getMainCardOrNull(): CardModel? {

    val realCards = filter { !it.isVirtual }
    val virtualCards = filter { it.isVirtual }

    if (realCards.isNotEmpty()) {
        return realCards.firstCard()
    } else if (virtualCards.isNotEmpty()) {
        return virtualCards.firstCard()
    }

    return null
}