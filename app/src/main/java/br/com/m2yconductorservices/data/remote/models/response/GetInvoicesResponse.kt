package br.com.m2yconductorservices.data.remote.models.response

class GetInvoicesResponse (
    val content: List<GetInvoicesContent>
)

class GetInvoicesContent(
    val id: Int,
    val idConta: Int,
    val nossoNumero: String,
    val dataVencimento: String,
    val valorBoleto: Float,
    val idTipoBoleto: Int
)