package br.com.m2yconductorservices.data.remote.models.response

data class BarcodeValidationResponse(var Message: MessageResponse?,
                                     val DataReturn: DataReturnResponse?) {
    fun getCharges() = (DataReturn?.Result?.PaymentInfoNPC?.ComputedBillValues?.DiscountValueCalculated ?: 0f) + (DataReturn?.Result?.PaymentInfoNPC?.ComputedBillValues?.CalculatedInterestAmount
            ?: 0f) + (DataReturn?.Result?.PaymentInfoNPC?.ComputedBillValues?.CalculatedFineValue ?: 0f)
}

data class MessageResponse(var MessageId: Int?,
                           var Title: String?,
                           var Message: String?)

data class DataReturnResponse(var Barcode: String?,
                              var Result: DataReturnResultResponse?)

data class DataReturnResultResponse(
        val Success: Boolean?,
        val Message: DataReturnResultMessageResponse?,
        val BillPaymentTypeId: Int?,
        val BillPaymentTypeDescription: String?,
        val HasEnoughBalance: Boolean?,
        val WasPaid: Boolean?,
        val PaymentSchedulingDate: String?,
        val ReachedLimit: Boolean?,
        val ValidateBarCode: ValidateBarCodeResponse?,
        val PaymentInfoNPC: PaymentInfoNPCResponse?)

data class DataReturnResultMessageResponse(
        val MessageId: Int?,
        val Title: String?,
        val Message: String?
)

data class PaymentInfoNPCResponse(
        val Id: Int?,
        val ContractNumber: String?,
        val IdentificationNumber: String?,
        val DueDate: String?,
        val BillValue: Float?,
        val TotalAmountToCharge: Float?,
        val TaxBreakValue: Int?,
        val PaymentLimitDate: String?,
        val OpeningPaymentschedule: String?,
        val ClosingPaymentschedule: String?,
        val IsValidDate: Boolean?,
        val IsBeforeWindow: Boolean?,
        val IsValidWindow: Boolean?,
        val NextUtilDay: String?,
        val BarCodeNumber: String?,
        val DigitavelLine: String?,
        val Traders: TradersResponse?,
        val ReceivingDivergentValue: ReceivingDivergentValueResponse?,
        val ReceiptRules: ReceiptRulesResponse?,
        val ComputedBillValues: ComputedBillValuesResponse?,
        val BillStatus: BillStatusResponse?,
        val Params: ParamsResponse?)

data class ReceivingDivergentValueResponse(
        val Code: Int?,
        val Description: String?
)

data class TradersResponse(val Recipient: String?,
                           val RecipientDocument: String?,
                           val PayerName: String?,
                           val PayerDocument: String?)

data class ReceiptRulesResponse(val TypeOfPaymentMin: String?,
                                val TypeOfPaymentMax: String?,
                                val MinPaymentValue: Float?,
                                val MaxPaymentValue: Float?)

data class ComputedBillValuesResponse(val CalculatedInterestAmount: Float?,
                                      val CalculatedFineValue: Float?,
                                      val DiscountValueCalculated: Float?,
                                      val TotalAmountToCharge: Float?)

data class BillStatusResponse(val Code: Int?,
                              val Description: String?)

data class ParamsResponse(val OutOfDate: Boolean?,
                          val AptoForPayment: Boolean?,
                          val BlockPaymentOfDate: Boolean?)

data class ValidateBarCodeResponse(
        val Id: Int?,
        val Description: String?,
        val Value: Float?,
        val DueDate: String?, //yyyy-mm-dd
        val IsOutTime: Boolean?,
        val TimeWindow: Int?,
        val MinTime: String?,
        val MaxTime: String?,
        val IsDefaultTime: Boolean?,
        val PaymentType: Int?,
        val HasDueDate: Boolean?,
        val BarCodeNumber: String?,
        val CurrentDate: String? //yyyy-mm-dd
)