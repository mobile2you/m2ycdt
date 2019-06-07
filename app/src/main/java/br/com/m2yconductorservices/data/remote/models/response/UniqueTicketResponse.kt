package br.com.m2yconductorservices.data.remote.models.response

import br.com.m2yconductorservices.M2YCDTConstants
import br.com.m2yconductorservices.data.local.models.ReceiptBilheteModel
import br.com.m2yconductorservices.data.local.models.ReceiptModel
import br.com.m2yconductorservices.data.local.models.ReceiptType
import br.com.m2yconductorservices.utils.extensions.m2yCdtChangeDateFormat
import java.io.Serializable

data class UniqueTicketResponse(
    var recharges: List<UniqueRecharge>
) : Serializable

data class UniqueRecharge(
    var id: Int?,
    var idDablam: String?,
    var accountId: Int?,
    var idIssuer: Int?,
    var idAdjustment: Int?,
    var cardNumber: Int?,
    var creditType: Int?,
    var productCode: Int?,
    var value: Float?,
    var amount: Float?,
    var transactionCode: String?,
    var date: String?
) : Serializable

fun UniqueRecharge.toReceiptModel(): ReceiptModel {

    return ReceiptModel(
        id.toString(),
        transactionCode.toString(),
        date?.m2yCdtChangeDateFormat(M2YCDTConstants.COMMON_DATE_TIME_FORMAT, M2YCDTConstants.RECEIPT_DATE_FORMAT),
        ReceiptType.BILHETE,
        bilhete = ReceiptBilheteModel(cardNumber.toString(), value)
    )
}