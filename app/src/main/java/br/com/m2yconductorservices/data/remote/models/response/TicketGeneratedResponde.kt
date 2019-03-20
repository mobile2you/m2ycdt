package br.com.m2yconductorservices.data.remote.models.response

import br.com.m2yconductorservices.data.local.models.TicketModel
import br.com.m2yconductorservices.data.local.models.TicketType
import java.io.Serializable

data class TicketGeneratedResponse(
        val numeroDoDocumento: String?,
        val dataProcessamento: String?,
        val dataDocumento: String?,
        val dataVencimento: String?,
        val dataFechamento: String?,
        val valorBoleto: Float?,
        val nomeBeneficiario: String?,
        val documentoBeneficiario: String?,
        val agencia: String?,
        val codigoBeneficiario: String?,
        val numeroConvenio: String?,
        val digitoCodigoBeneficiario: String?,
        val carteira: String?,
        val nossoNumero: String?,
        val digitoNossoNumero: String?,
        val banco: String?,
        val aceite: Boolean?,
        val especie: String?,
        val nomePagador: String?,
        val documentoPagador: String?,
        val logradouroPagador: String?,
        val bairroPagador: String?,
        val cepPagador: String?,
        val cidadePagador: String?,
        val ufPagador: String?,
        val codigoDeBarras: String?,
        var linhaDigitavel: String?,
        val id: String?,
        val idConta: Long?,
        val enderecoCobrancaBeneficiario: String?,
        val status: Long?,
        val boletoRegistrado: Boolean?,
        val available: Boolean?,
        var taxa: Float? = null
) : Serializable

fun TicketGeneratedResponse.toTicketModel(type: TicketType): TicketModel{
    return TicketModel(type, id, linhaDigitavel, valorBoleto ?:  0f, null,
            dataVencimento, idConta.toString(), idConta.toString())
}

//enum class TicketStatus(@StringRes val statusName: Int, val statusServerId: Long) {
//    GENERATED(R.string.ticket_status_generated, 1),
//    PAID(R.string.ticket_status_paid, 4),
//    REGISTERED(R.string.ticket_status_registered, 3);
//
//    fun getStatusName(context: Context) = context.getString(statusName)
//}
//
//fun TicketGeneratedResponse.canPay(): Boolean {
//    val dueDate = this.dataVencimento?.m2yCdtDateFromString(M2YCDTConstants.TICKET_DATE_FORMAT)
//    return this.status != TicketStatus.PAID.statusServerId && dueDate?.isPast() == false
//}