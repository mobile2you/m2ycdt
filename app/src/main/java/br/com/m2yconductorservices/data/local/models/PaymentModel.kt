package br.com.m2yconductorservices.data.local.models

import br.com.m2yconductorservices.M2YCDTConstants
import br.com.m2yconductorservices.data.local.M2YCDTPersistUserInformation
import br.com.m2yconductorservices.data.remote.models.response.BarcodeValidationResponse
import br.com.m2yconductorservices.utils.extensions.m2yCdtChangeDateFormat
import br.com.m2yconductorservices.utils.extensions.m2yCdtDateFromString
import br.com.m2yconductorservices.utils.extensions.m2yCdtFormat
import com.google.gson.Gson
import java.io.Serializable
import java.lang.Exception
import java.util.*

class PaymentModel(var messageId: Int = 0,
                   var value: Float? = 0f,
                   var valueWithCharges: Float? = 0f,
                   var barcode: String? = null,
                   var description: String? = null,
                   var name: String? = null,
                   var social: String? = null,
                   var document: String? = null,
                   var discount: Float? = 0f,
                   var interest: Float? = 0f,
                   var fine: Float? = 0f,
                   var charges: Float? = 0f,
                   var dueDate: String? = null,
                   var paymentHour: String? = null,
                   var accomplished: String? = null,
                   var paidOut: String? = null,
                   var minTime: String? = null,
                   var maxTime: String? = null,
                   var isOutTime: Boolean? = null,
                   var paymentLimitDate: String? = null,
                   var hasDueDate: Boolean? = null,
                   var maxPayment: Float? = null,
                   var minPayment: Float? = null,
                   var aptoForPayment: Boolean? = null,
                   var blockPaymentOfDate: Boolean? = null,
                   var isNPC: Boolean? = null,
                   var openingPaymentSchedule: String? = null,
                   var closingPaymentSchedule: String? = null,
                   var isValidWindow: Boolean? = null,
                   var message: String? = null,
                   var password: String?,
                   var cardId: String?) : Serializable {

    val paymentLimitDisplay: String
        get() = this.paymentLimitDate?.m2yCdtChangeDateFormat(M2YCDTConstants.TICKET_DATE_FORMAT, M2YCDTConstants.COMMON_DATE_FORMAT)
                ?: ""

    val paymentLimitIsAfterToday: Boolean
        get() {
            val calToday = Calendar.getInstance()
            val calLimit = Calendar.getInstance()

            val dateLimit = this.paymentLimitDate?.m2yCdtDateFromString(M2YCDTConstants.TICKET_DATE_FORMAT)
            return if (dateLimit == null) {
                false
            } else {
                calLimit.time = dateLimit
                calToday.after(calLimit)
            }
        }

    val jsonObject: PaymentTicketJson?
        get() {
            return try {
                val ticket = Gson().fromJson(description, PaymentTicketJson::class.java)
                ticket.barcode = barcode
                ticket.amount = value
                ticket
            } catch (ex: Exception) {
                null
            }
        }

    fun eraseExtraInformation() {
        this.discount = 0f
        this.interest = 0f
        this.fine = 0f
        this.charges = 0f
    }

    class PaymentTicketJson(
            var barcode: String?,
            var bank: String?,
            var name: String?,
            var cpfOrCNPJ: String?,
            var discount: Float?,
            var interest: Float?,
            var fine: Float?,
            var charges: Float?,
            var expiration: String?, //dd/MM/yyyy
            var amount: Float?,
            var paymentDate: String? //dd/MM/yyyy HH:mm:ss
    ) : Serializable {

        val totalCharges: Float
            get() = (charges ?: 0f) + (fine ?: 0f)

    }

}

fun PaymentModel.toTicketModel(accountId: String): TicketModel {
    return TicketModel(type = TicketType.PAYMENT, barcode = barcode,
            amount = value ?: 0f, sourceAccount = accountId, destination = jsonObject?.name ?: "",
            document = jsonObject?.cpfOrCNPJ ?: document, discount = jsonObject?.discount
            ?: discount ?: 0f, interest = jsonObject?.interest ?: interest ?: 0f,
            fine = jsonObject?.fine ?: fine ?: 0f, charges = jsonObject?.charges ?: charges
    ?: 0f, dueDate = jsonObject?.expiration ?: dueDate)
}

fun PaymentModel.toReceiptModel(): ReceiptModel {
    return ReceiptModel("", "", jsonObject?.paymentDate?.m2yCdtChangeDateFormat(M2YCDTConstants.CDT_DATE_FORMAT, M2YCDTConstants.COMMON_DATE_TIME_FORMAT), type = ReceiptType.PAYMENT, payment = ReceiptPaymentModel(barcode, jsonObject?.name ?: description,
            jsonObject?.expiration, jsonObject?.paymentDate?.m2yCdtChangeDateFormat(M2YCDTConstants.CDT_DATE_FORMAT, M2YCDTConstants.COMMON_DATE_FORMAT), jsonObject?.discount, jsonObject?.fine,
            jsonObject?.charges, jsonObject?.interest, jsonObject?.cpfOrCNPJ, jsonObject?.paymentDate,
            jsonObject?.amount, M2YCDTPersistUserInformation.accountId()))
}


fun BarcodeValidationResponse.toPaymentModel(): PaymentModel {

    when {
        DataReturn?.Result?.BillPaymentTypeId == BillPaymentType.NORMAL.serverId ->
            return PaymentModel(Message?.MessageId ?: -1,
                    DataReturn.Result?.ValidateBarCode?.Value,
                    DataReturn.Result?.ValidateBarCode?.Value,
                    DataReturn.Result?.ValidateBarCode?.BarCodeNumber,
                    DataReturn.Result?.ValidateBarCode?.Description,
                    null,
                    null,
                    null,
                    DataReturn.Result?.PaymentInfoNPC?.ComputedBillValues?.DiscountValueCalculated,
                    DataReturn.Result?.PaymentInfoNPC?.ComputedBillValues?.CalculatedInterestAmount,
                    DataReturn.Result?.PaymentInfoNPC?.ComputedBillValues?.CalculatedFineValue,
                    getCharges(),
                    if (DataReturn?.Result?.ValidateBarCode?.HasDueDate == false)
                        java.util.Calendar.getInstance().m2yCdtFormat(M2YCDTConstants.TICKET_DATE_FORMAT)
                    else
                        DataReturn.Result?.ValidateBarCode?.DueDate,
                    null,
                    null,
                    null,
                    DataReturn.Result?.ValidateBarCode?.MinTime,
                    DataReturn.Result?.ValidateBarCode?.MaxTime,
                    DataReturn.Result?.ValidateBarCode?.IsOutTime,
                    null,
                    DataReturn.Result?.ValidateBarCode?.HasDueDate,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    DataReturn.Result?.Message?.Message, null, null)
        DataReturn?.Result?.BillPaymentTypeId == BillPaymentType.NPC.serverId ->
            return PaymentModel(Message?.MessageId ?: -1,
                    DataReturn.Result?.PaymentInfoNPC?.TotalAmountToCharge,
                    DataReturn.Result?.PaymentInfoNPC?.ComputedBillValues?.TotalAmountToCharge,
                    DataReturn.Result?.PaymentInfoNPC?.DigitavelLine,
                    DataReturn.Result?.ValidateBarCode?.Description,
                    DataReturn.Result?.PaymentInfoNPC?.Traders?.Recipient,
                    DataReturn.Result?.PaymentInfoNPC?.Traders?.Recipient,
                    DataReturn.Result?.PaymentInfoNPC?.Traders?.RecipientDocument,
                    DataReturn.Result?.PaymentInfoNPC?.ComputedBillValues?.DiscountValueCalculated,
                    DataReturn.Result?.PaymentInfoNPC?.ComputedBillValues?.CalculatedInterestAmount,
                    DataReturn.Result?.PaymentInfoNPC?.ComputedBillValues?.CalculatedFineValue,
                    getCharges(),
                    DataReturn.Result?.PaymentInfoNPC?.DueDate,
                    null,
                    null,
                    null,
                    DataReturn.Result?.ValidateBarCode?.MinTime,
                    DataReturn.Result?.ValidateBarCode?.MaxTime,
                    DataReturn.Result?.ValidateBarCode?.IsOutTime,
                    DataReturn.Result?.PaymentInfoNPC?.PaymentLimitDate,
                    DataReturn.Result?.ValidateBarCode?.HasDueDate,
                    DataReturn.Result?.PaymentInfoNPC?.ReceiptRules?.MaxPaymentValue,
                    DataReturn.Result?.PaymentInfoNPC?.ReceiptRules?.MaxPaymentValue,
                    DataReturn.Result?.PaymentInfoNPC?.Params?.AptoForPayment,
                    DataReturn.Result?.PaymentInfoNPC?.Params?.BlockPaymentOfDate,
                    true,
                    DataReturn.Result?.PaymentInfoNPC?.OpeningPaymentschedule,
                    DataReturn.Result?.PaymentInfoNPC?.ClosingPaymentschedule,
                    DataReturn.Result?.PaymentInfoNPC?.IsValidWindow,
                    DataReturn.Result?.Message?.Message, null, null)
        else -> return PaymentModel(Message?.MessageId ?: -1,
                DataReturn?.Result?.PaymentInfoNPC?.BillValue,
                DataReturn?.Result?.PaymentInfoNPC?.ComputedBillValues?.TotalAmountToCharge,
                DataReturn?.Result?.PaymentInfoNPC?.DigitavelLine,
                DataReturn?.Result?.ValidateBarCode?.Description,
                DataReturn?.Result?.PaymentInfoNPC?.Traders?.Recipient,
                DataReturn?.Result?.PaymentInfoNPC?.Traders?.Recipient,
                DataReturn?.Result?.PaymentInfoNPC?.Traders?.RecipientDocument,
                DataReturn?.Result?.PaymentInfoNPC?.ComputedBillValues?.DiscountValueCalculated,
                DataReturn?.Result?.PaymentInfoNPC?.ComputedBillValues?.CalculatedInterestAmount,
                DataReturn?.Result?.PaymentInfoNPC?.ComputedBillValues?.CalculatedFineValue,
                getCharges(),
                if (DataReturn?.Result?.ValidateBarCode?.HasDueDate == false) java.util.Calendar.getInstance().m2yCdtFormat(M2YCDTConstants.TICKET_DATE_FORMAT) else DataReturn?.Result?.PaymentInfoNPC?.DueDate,
                null,
                null,
                null,
                DataReturn?.Result?.ValidateBarCode?.MinTime,
                DataReturn?.Result?.ValidateBarCode?.MaxTime,
                DataReturn?.Result?.ValidateBarCode?.IsOutTime,
                DataReturn?.Result?.PaymentInfoNPC?.PaymentLimitDate,
                DataReturn?.Result?.ValidateBarCode?.HasDueDate,
                DataReturn?.Result?.PaymentInfoNPC?.ReceiptRules?.MaxPaymentValue,
                DataReturn?.Result?.PaymentInfoNPC?.ReceiptRules?.MaxPaymentValue,
                DataReturn?.Result?.PaymentInfoNPC?.Params?.AptoForPayment,
                DataReturn?.Result?.PaymentInfoNPC?.Params?.BlockPaymentOfDate,
                null,
                null,
                null,
                null,
                DataReturn?.Result?.Message?.Message, null, null)
    }


}

enum class BillPaymentType(val serverId: Int) {
    NPC(1),
    NORMAL(2)
}