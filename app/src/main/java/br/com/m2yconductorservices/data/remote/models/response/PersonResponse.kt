package br.com.m2yconductorservices.data.remote.models.response

class PersonResponse(
    val id: Int,
    val nome: String,
    val tipo: String,
    val cpf: String,
    val cnpj: String,
    val dataNascimento: String,
    val sexo: String,
    val numeroIdentidade: String,
    val orgaoExpedidorIdentidade: String,
    val unidadeFederativaIdentidade: String,
    val dataEmissaoIdentidade: String
)