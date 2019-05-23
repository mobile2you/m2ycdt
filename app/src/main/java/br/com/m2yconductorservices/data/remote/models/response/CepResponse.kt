package br.com.m2yconductorservices.data.remote.models.response

import java.io.Serializable

data class CepResponse(val cep: String,
                       val endereco: String,
                       val bairro: String,
                       val cidade: String,
                       val uf: String

): Serializable