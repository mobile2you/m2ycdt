package br.com.m2yconductorservices.data.remote.models.response

class PersonResponse(
    var id: Int,
    var nome: String?,
    var tipo: String,
    var cpf: String?,
    var cnpj: String?,
    var dataNascimento: String?,
    var sexo: String?,
    var numeroIdentidade: String?,
    var orgaoExpedidorIdentidade: String?,
    var unidadeFederativaIdentidade: String?,
    var dataEmissaoIdentidade: String?
)