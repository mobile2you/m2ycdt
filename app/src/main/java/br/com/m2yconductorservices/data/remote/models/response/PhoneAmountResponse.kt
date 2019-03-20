package br.com.m2yconductorservices.data.remote.models.response

class PhoneAmountResponse(
        val orderId: String,
        val options: List<PhoneAmountOption>
)

class PhoneAmountOption(
        val fixed_amounts: List<PhoneAmountValue>
)

class PhoneAmountValue(
        val amount: String
)