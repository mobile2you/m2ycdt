package br.com.m2yconductorservices.data.remote.models.response

import android.provider.SyncStateContract
import br.com.m2yconductorservices.M2YCDTConstants
import br.com.m2yconductorservices.data.local.M2YCDTPersistUserInformation
import br.com.m2yconductorservices.data.local.models.ReceiptModel
import br.com.m2yconductorservices.data.local.models.ReceiptPaymentModel
import br.com.m2yconductorservices.utils.extensions.m2yCdtChangeDateFormat

data class TicketPaymentResponse(
        val barCodeNumber: String,
        val dueDate: String,
        val transactionDate: String
)

data class TicketPaymentV1Response(
        val idAccount: String?,
        val idAdjustment: String?,
        val barCodeNumber: String?,
        val dueDate: String?,
        val description: String?,
        val assignor: String?,
        val assignorDocument: String?,
        val discount: Float?,
        val interest: Float?,
        val fine: Float?,
        val amount: Float?,
        val transactionCode: String?,
        val transactionDate: String?,
        val idPaymentConfirmation: String?,
        val status: String?
)

fun TicketPaymentV1Response.toReceiptModel(): ReceiptModel {
        return ReceiptModel(
                id = idPaymentConfirmation,
                auth = "",
                date = transactionDate?.m2yCdtChangeDateFormat(M2YCDTConstants.CDT_DATE_FORMAT, M2YCDTConstants.COMMON_DATE_TIME_FORMAT),
                type = br.com.m2yconductorservices.data.local.models.ReceiptType.PAYMENT,
                payment = ReceiptPaymentModel(
                        id = "",
                        name = assignor,
                        expiration = dueDate?.split("T")?.first()?.m2yCdtChangeDateFormat(
                                M2YCDTConstants.TICKET_DATE_FORMAT,
                                M2YCDTConstants.COMMON_DATE_FORMAT
                        ),
                        paymentDate = transactionDate?.m2yCdtChangeDateFormat(
                                M2YCDTConstants.CDT_DATE_FORMAT,
                                M2YCDTConstants.COMMON_DATE_FORMAT
                        ),
                        discount = discount ?: 0F,
                        fine = fine ?: 0F,
                        charges = (fine ?: 0f) + (interest ?: 0f),
                        interest = interest ?: 0F,
                        to = assignorDocument,
                        date = "",
                        value = amount,
                        paymentSource = M2YCDTPersistUserInformation.accountId()
                )
        )


}