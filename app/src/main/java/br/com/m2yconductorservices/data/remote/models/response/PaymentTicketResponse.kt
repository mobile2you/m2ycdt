package br.com.m2yconductorservices.data.remote.models.response

import br.com.m2yconductorservices.M2YCDTConstants
import br.com.m2yconductorservices.data.local.models.PaymentModel
import br.com.m2yconductorservices.utils.extensions.m2yCdtChangeDateFormat
import com.google.gson.Gson
import java.io.Serializable
import java.lang.Exception

class PaymentTicketResponse(var id: Int?,
                            var idAdjustment: Float?,
                            var idIssuer: Float?,
                            var uniqueIdDablan: String?,
                            var account: Double?,
                            var amount: Float?,
                            var discount: Float?,
                            var interest: Float?,
                            var fine: Float?,
                            var charges: Float?,
                            var barCode: String?,
                            var dueDate: String,
                            var description: String?) : Serializable {

    val displayDescription: String
        get() = jsonObject?.name ?: ""

    val jsonObject: PaymentTicketJson?
        get() {
            return try {
                Gson().fromJson(description, PaymentTicketJson::class.java)
            } catch (ex: Exception) {
                PaymentTicketJson(null, null, description, null, null, null, null, null, null, null, null)
            }
        }

    class PaymentTicketJson(
            var barCode: String?,
            var bank: String?,
            var name: String?,
            var socialReason: String?,
            var cpfOrCNPJ: String?,
            var discount: Float?,
            var interest: Float?,
            var fine: Float?,
            var charges: Float?,
            var expiration: String?, //dd/MM/yyyy
            var paymentDate: String? //dd/MM/yyyy HH:mm:ss
    ) : Serializable {

        val totalCharges: Float
            get() = (charges ?: 0f) + (fine ?: 0f)

    }
}

fun PaymentTicketResponse.toPaymentModel(): PaymentModel {
    return PaymentModel(valueWithCharges = amount, barcode = barCode,
            dueDate = dueDate.m2yCdtChangeDateFormat(M2YCDTConstants.CDT_TICKET_DATE_FORMAT, M2YCDTConstants.COMMON_DATE_FORMAT), password = null, cardId = null)
}

