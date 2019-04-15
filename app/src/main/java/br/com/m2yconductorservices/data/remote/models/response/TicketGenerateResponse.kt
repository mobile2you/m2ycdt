package br.com.m2yconductorservices.data.remote.models.response

import br.com.m2yconductorservices.M2YCDTConstants
import br.com.m2yconductorservices.data.local.models.VoucherItemModel
import br.com.m2yconductorservices.data.local.models.VoucherTypeItem
import br.com.m2yconductorservices.utils.extensions.m2yCdtChangeDateFormat

data class TicketGenerateResponse(
        var id: String?,
        var bank: String?,
        var cdt_id: String?,
        var value: Float?,
        var expiration_date: String?,
        var created_at: String?,
        var accountId: String?,
        var bar_code: String?,
        var paid: Boolean?,
        var pdf_id: String?
)

fun TicketGenerateResponse.toVoucher(): VoucherItemModel {
    return VoucherItemModel(id = id?.toString() ?: "", nameRes = VoucherTypeItem.RECHARGE.nameRes,
            date = expiration_date?.m2yCdtChangeDateFormat(M2YCDTConstants.CDT_DATE_FORMAT, M2YCDTConstants.CDT_TICKET_DATE_FORMAT).toString(),
            subject = cdt_id ?: "", amount = this.value ?: 0f)
}