package br.com.m2yconductorservices.data.local.models

import java.io.Serializable

enum class ReceiptType : Serializable {
    TRANSFER,
    PAYMENT,
    RECHARGE
}