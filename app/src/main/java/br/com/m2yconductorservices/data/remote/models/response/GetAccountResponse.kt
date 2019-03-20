package br.com.m2yconductorservices.data.remote.models.response

data class GetAccountResponse(
        var id: Int?,
        var idPessoa: Int?
)

data class GetPeopleResponse(
        var content: List<GetPeopleResponse>,
        var id: Int?,
        var nome: String?,
        var tipo: String?,
        var ddd: String?,
        var cpf: String?,
        var cnpj: String,
        var email: String?,
        var telefone: String?,
        var dataNascimento: String?,
        var numeroIdentidade: String?,
        var orgaoExpedidorIdentidade: String?,
        var sexo: String?,
        var unidadeFederativaIdentidade: String?,
        var dataEmissaoIdentidade: String?
)

data class GetPeopleDetailResponse(
        var content: List<PeopleDetailResponse>?,
        var first: Boolean?,
        var firstPage: Boolean?,
        var hasContent: Boolean?,
        var hasNextPage: Boolean?,
        var hasPreviousPage: Boolean?,
        var last: Boolean?,
        var nextPage: Boolean?,
        var number: Int?,
        var numberOfElements: Int?,
        var previousPage: Int?,
        var size: Int?,
        var totalElements: Int?,
        var totalPages: Int?
)

data class PeopleDetailResponse(
        var idPessoa: Int?,
        var nomeDaMae: String?,
        var idEstadoCivil: Int?,
        var email: String?
)