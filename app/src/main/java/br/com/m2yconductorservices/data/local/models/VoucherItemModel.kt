package br.com.m2yconductorservices.data.local.models

import br.com.m2yconductorservices.M2YCDTConstants
import br.com.m2yconductorservices.data.remote.models.response.PaymentTicketResponse
import br.com.m2yconductorservices.data.remote.models.response.RechargeVoucherResponse
import br.com.m2yconductorservices.data.remote.models.response.Transferp2pResponse
import br.com.m2yconductorservices.data.remote.models.response.VoucherBankResponse
import br.com.m2yconductorservices.utils.extensions.m2yCdtChangeDateFormat
import br.com.m2yconductorservices.utils.extensions.m2yCdtDateFromString
import java.util.*

data class VoucherItemModel(
        val id: String,
        val nameRes: Int,
        val date: String,
        val subject: String,
        val amount: Float
) {
    val dateCal: Calendar
        get() {
            val cal = Calendar.getInstance()
            cal?.time = date.m2yCdtDateFromString(M2YCDTConstants.CDT_TICKET_DATE_FORMAT)
            return cal
        }


    val dateDays: String
        get() = date.m2yCdtChangeDateFormat(M2YCDTConstants.CDT_TICKET_DATE_FORMAT, M2YCDTConstants.EXTRACT_DATE_FORMAT)
}

fun PaymentTicketResponse.toVoucher(): VoucherItemModel {
    return VoucherItemModel(id = id?.toString() ?: "", nameRes = VoucherTypeItem.PAYMENT.nameRes,
            date = dueDate.m2yCdtChangeDateFormat(M2YCDTConstants.CDT_DATE_FORMAT, M2YCDTConstants.CDT_TICKET_DATE_FORMAT),
            subject = displayDescription, amount = this.amount ?: 0f)
}

fun Transferp2pResponse.toVoucher(): VoucherItemModel {
    return VoucherItemModel(id = id?.toString() ?: "",
            nameRes = VoucherTypeItem.P2P_TRANSFER.nameRes,
            date = date?.m2yCdtChangeDateFormat(M2YCDTConstants.CDT_DATE_FORMAT, M2YCDTConstants.CDT_TICKET_DATE_FORMAT, isUTC = false)
                    ?: "", subject = "Conta ${destinationAccount?.toInt()}", amount = amount ?: 0f)
}

fun VoucherBankResponse.toVoucher(): VoucherItemModel {

    var agency: String?
    if (agencyFavored?.toInt().toString().length == 1) {
        agency = "000${agencyFavored?.toInt()}"
    } else if (agencyFavored?.toInt().toString().length == 2) {
        agency = "00${agencyFavored?.toInt()}"

    } else if (agencyFavored?.toInt().toString().length == 3) {
        agency = "0${agencyFavored?.toInt()}"
    } else {
        agency = "${agencyFavored?.toInt()}"
    }

    var banco: String?
    if (bankFavored?.toInt().toString().length == 1) {
        banco = "00${bankFavored?.toInt()}"
    } else if (bankFavored?.toInt().toString().length == 2) {
        banco = "0${bankFavored?.toInt()}"
    } else {
        banco = "${bankFavored?.toInt()}"
    }


    return VoucherItemModel(id = id?.toString() ?: "",
            nameRes = VoucherTypeItem.BANK_TRANSFER.nameRes,
            date = transferenceDate?.m2yCdtChangeDateFormat(M2YCDTConstants.CDT_DATE_FORMAT, M2YCDTConstants.CDT_TICKET_DATE_FORMAT)
                    ?: "", subject = "Banco $banco - $agency - ${accountFavored?.toInt()}-$digitAccountFavored", amount = value
            ?: 0f)
}

fun RechargeVoucherResponse.toVoucher(): VoucherItemModel {
    val phone: String

    if (phoneNumber.length == 9) {
        val phoneDigit = phoneNumber.substring(0, 1)
        val firstForNumbers = phoneNumber.substring(1, 5)
        val lastForNumbers = phoneNumber.substring(5, 9)
        phone = "$phoneDigit $firstForNumbers-$lastForNumbers"
    } else {
        phone = phoneNumber
    }

    return VoucherItemModel(id = orderId ?: "", nameRes = VoucherTypeItem.RECHARGE.nameRes,
            date = rechargeDate.m2yCdtChangeDateFormat(M2YCDTConstants.RECHARGE_DATE_FORMAT, M2YCDTConstants.CDT_TICKET_DATE_FORMAT),
            subject = "$dealerName - ($ddd) $phone", amount = amountFloat)
}