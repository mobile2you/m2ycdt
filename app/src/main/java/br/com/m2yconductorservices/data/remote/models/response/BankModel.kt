package br.com.m2yconductorservices.data.remote.models.response

import java.io.Serializable

data class BankModel(
        val id: String,
        val name: String,
        val color: String,
        val code: Int
) : Serializable