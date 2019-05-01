package br.com.m2yconductorservices.data.remote.models.response

import br.com.m2yconductorservices.data.local.models.ExtractModel
import br.com.m2yconductorservices.utils.extensions.m2yCdtNormalizeSpaces

data class ExtractResponse(val description: String?,
                           val nomeEstabelecimento: String?,
                           val date: String?,
                           val idEventoAjuste: Float?,
                           val codigoAutorizacao: String?,
                           val flagCredito: Int?,
                           val codigoMCC: Int?,
                           val idTransferencia: String?,
                           val value: Float?,
                           val refund_status: String?)

class ExtractContainerModel(val extractList: List<ExtractModel>,
                            val isLastPage: Boolean)

fun ExtractResponse.toModel(): ExtractModel {

    val title: String?

    if (!nomeEstabelecimento.isNullOrBlank()) {
        title = nomeEstabelecimento
    } else {
        title = description
    }

    return ExtractModel(id = idTransferencia ?: "id", date = this.date ?: "",
            title = title?.m2yCdtNormalizeSpaces() ?: "", amount = (value ?: 0f), codigoMCC = codigoMCC,
            refund_status = refund_status)
}