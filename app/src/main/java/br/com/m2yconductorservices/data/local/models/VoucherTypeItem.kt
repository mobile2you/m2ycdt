package br.com.m2yconductorservices.data.local.models

import android.support.annotation.StringRes
import br.com.m2yconductorservices.R

enum class VoucherTypeItem(@StringRes val nameRes: Int) {
    PAYMENT(R.string.cdt_vouchers_payment),
    BANK_TRANSFER(R.string.cdt_vouchers_bank_transfer),
    P2P_TRANSFER(R.string.cdt_vouchers_p2p_transfer),
    RECHARGE(R.string.cdt_vouchers_recharge),
    UNIQUE_TICKET(R.string.cdt_vouchers_recharge_unique)
}