package br.com.m2yconductorservices.data.remote.models.response

import br.com.m2yconductorservices.M2YCDTConstants
import br.com.m2yconductorservices.data.local.models.BalanceModel
import br.com.m2yconductorservices.utils.extensions.m2yCdtDateFromString
import java.io.Serializable

data class CardPageResponse(
        var saldo: Float?,
        var peopleId: Int?,
        var cards: List<CardResponse>?
) : Serializable {

    fun getMainCard(): CardResponse? {
        val realCards = cards?.filter { it.cartaoVirtual == 0 } ?: listOf()
        val cardsArray = cards?.filter { it.cartaoVirtual == 1 } ?: listOf()

        if (realCards.count() > 0) {
            return realCards.firstCard()
        } else if (cardsArray.count() > 0) {
            return cardsArray.firstCard()
        }

        return null
    }
}

fun List<CardResponse>?.firstCard(): CardResponse? {
    return this?.asSequence()?.sortedBy { it.dataEmissao?.m2yCdtDateFromString(M2YCDTConstants.CDT_TICKET_RESALES_DATE_FORMAT)?.time }?.firstOrNull()
}


data class CardResponse(
        var id: String,
        val idCartao: Int?,
        val idStatus: String?,
        val numeroBin: String?,
        var numeroCartao: String?,
        val dataEmissao: String?,
        val cartaoVirtual: Int?,
        val idStatusCartao: Int?,
        var statusCartao: String?,
        val codigoDesbloqueio: String?
) : Serializable

fun CardPageResponse.toModel() = BalanceModel(saldo)