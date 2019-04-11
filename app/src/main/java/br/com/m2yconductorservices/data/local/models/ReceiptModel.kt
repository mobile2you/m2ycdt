package br.com.m2yconductorservices.data.local.models

import br.com.m2yconductorservices.M2YCDTConstants
import br.com.m2yconductorservices.data.remote.models.request.RechargeRequest
import br.com.m2yconductorservices.data.remote.models.response.*
import br.com.m2yconductorservices.utils.extensions.m2yCdtChangeDateFormat
import br.com.m2yconductorservices.utils.extensions.m2yCdtFormatCurrencyBRL
import java.io.Serializable
import kotlin.math.roundToInt

data class ReceiptModel(
        val id: String?,
        val auth: String?,
        val date: String?,
        val type: ReceiptType,
        val transfer: ReceiptTransferModel? = null,
        val payment: ReceiptPaymentModel? = null,
        val recharge: ReceiptRechargeModel? = null
) : Serializable

data class ReceiptTransferModel(
        var account: String?,
        val value: Float?,
        var destinyName: String?,
        var destinyDocument: String?,
        val destinyBank: String?,
        val destinyAgency: String?,
        var destinyAccount: String?,
        val type: TransferType
) : Serializable

enum class TransferType(val type: Int) {
    BANK(0),
    P2P(1),
    RESSELER(2),
    RECHARGE(3)
}

data class ReceiptPaymentModel(
        val id: String?,
        val name: String?,
        val expiration: String?,
        val paymentDate: String?,
        val discount: Float?,
        val fine: Float?,
        val charges: Float?,
        val interest: Float?,
        val to: String?,
        val date: String?,
        val value: Float?,
        val paymentSource: String?
) : Serializable

data class ReceiptRechargeModel(
        val phoneNumber: String,
        val operator: String,
        val name: String?,
        val value: String?
) : Serializable

fun TransferResellerResponse.toReceiptModel(): ReceiptModel {
    return ReceiptModel(id?.toString(), transactionCode, transactionDate?.m2yCdtChangeDateFormat(M2YCDTConstants.CDT_DATE_FORMAT, M2YCDTConstants.RECEIPT_DATE_FORMAT),
            ReceiptType.TRANSFER, transfer = ReceiptTransferModel("Conta $destinationAccount", amount, null, null, null, null, null, TransferType.RESSELER))
}


fun TransferBankResponse.toReceiptModel(): ReceiptModel {
    return ReceiptModel(idOriginAccount.toString(), this.transactionCode, date.m2yCdtChangeDateFormat(M2YCDTConstants.CDT_DATE_FORMAT, M2YCDTConstants.RECEIPT_DATE_FORMAT),
            ReceiptType.TRANSFER,
            transfer = ReceiptTransferModel(idOriginAccount.toInt().toString(),
                    value, beneficiary.name, beneficiary.docIdCpfCnpjEinSSN.toString(), bankName, beneficiary.agency.toString(), "${beneficiary.account}-${beneficiary.accountDigit}", TransferType.BANK))
}

fun VoucherBankResponse.toReceiptModel(): ReceiptModel {
//    val data = "${this.transferBankName} \nAgência ${this.agencyFavored?.toString()?.m2yCdtFillWithChars(4, '0', CustomString.BEFORE)} \n" +
//            "Conta ${this.accountFavored?.toString()?.m2yCdtFillWithChars(5, '0', CustomString.BEFORE)} - ${this.digitAccountFavored}" +
//            "\n\n${this.nameFavored}"
    return ReceiptModel(id?.toString(), this.transactionCode, transferenceDate?.m2yCdtChangeDateFormat(M2YCDTConstants.CDT_DATE_FORMAT, M2YCDTConstants.RECEIPT_DATE_FORMAT),
            ReceiptType.TRANSFER, transfer = ReceiptTransferModel(idOriginAccount?.toInt().toString(), value, nameFavored, cpfCnpjFavored.toString(), bankName, agencyFavored?.toInt().toString(), "${accountFavored?.toInt()}-${digitAccountFavored}", TransferType.BANK))
}

fun Transferp2pResponse.toReceiptModel(): ReceiptModel {
    return ReceiptModel(id?.toString(), this.transactionCode,
            date?.m2yCdtChangeDateFormat(M2YCDTConstants.CDT_DATE_FORMAT, M2YCDTConstants.RECEIPT_DATE_FORMAT),
            ReceiptType.TRANSFER,
            transfer = ReceiptTransferModel(originalAccount?.toInt().toString(),
                    amount,
                    jsonObject?.name,
                    jsonObject?.cpfOrCNPJ.toString(),
                    jsonObject?.bank,
                    null,
                    destinationAccount?.roundToInt().toString(),
                    TransferType.P2P))
}

fun PaymentTicketResponse.toReceiptModel(): ReceiptModel {
    var cpfOrCnpj: String? = null
    var date: String? = null
    this.jsonObject?.let {
        cpfOrCnpj = it.cpfOrCNPJ
        date = it.paymentDate?.m2yCdtChangeDateFormat(M2YCDTConstants.CDT_DATE_FORMAT, M2YCDTConstants.RECEIPT_DATE_FORMAT)
    } ?: run {
        cpfOrCnpj = description
        date = dueDate.m2yCdtChangeDateFormat(M2YCDTConstants.CDT_DATE_FORMAT, M2YCDTConstants.RECEIPT_DATE_FORMAT)
    }

    val payment = ReceiptPaymentModel(barCode, jsonObject?.name ?: "", jsonObject?.expiration
            ?: "", jsonObject?.paymentDate ?: "",
            jsonObject?.discount, jsonObject?.fine, jsonObject?.charges, jsonObject?.interest, cpfOrCnpj
            ?: "", date ?: "", amount, account?.toInt().toString())

    return ReceiptModel(id?.toString(), "",
            this.dueDate.m2yCdtChangeDateFormat(M2YCDTConstants.CDT_DATE_FORMAT, M2YCDTConstants.RECEIPT_DATE_FORMAT),
            ReceiptType.PAYMENT, payment = payment)
}

fun RechargeRequest.toReceiptModel(): ReceiptModel {
    val recharge = ReceiptRechargeModel(phoneNumber = "($ddd) $phoneNumber", operator = dealerName, name = nickname, value =  amount.m2yCdtFormatCurrencyBRL())

    return ReceiptModel(orderId, auth, date.m2yCdtChangeDateFormat(M2YCDTConstants.COMMON_DATE_FORMAT, M2YCDTConstants.RECEIPT_DATE_FORMAT),
            ReceiptType.RECHARGE, recharge = recharge)
}

fun RechargeVoucherResponse.toReceiptModel(): ReceiptModel {
    val phone: String

    phone = if (phoneNumber.length == 9) {
        val phoneDigit = phoneNumber.substring(0, 1)
        val firstForNumbers = phoneNumber.substring(1, 5)
        val lastForNumbers = phoneNumber.substring(5, 9)
        "$phoneDigit $firstForNumbers-$lastForNumbers"
    } else {
        phoneNumber
    }

    val recharge = ReceiptRechargeModel(phoneNumber = "($ddd) $phone", operator = dealerName, name = nickname, value =  amountFloat.m2yCdtFormatCurrencyBRL())

    return ReceiptModel(orderId, transactionCode, rechargeDate.m2yCdtChangeDateFormat(M2YCDTConstants.RECHARGE_DATE_FORMAT, M2YCDTConstants.RECEIPT_DATE_FORMAT),
            ReceiptType.RECHARGE, recharge = recharge)
}