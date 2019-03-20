package br.com.m2yconductorservices.data.remote.services

import br.com.m2yconductorservices.M2YCDTNetworkConstants
import br.com.m2yconductorservices.data.remote.models.request.AccountIdIntRequest
import br.com.m2yconductorservices.data.remote.models.request.TicketPaymentRequest
import br.com.m2yconductorservices.data.remote.models.request.ValidateBarCodeRequest
import br.com.m2yconductorservices.data.remote.models.response.BarcodeValidationResponse
import br.com.m2yconductorservices.data.remote.models.response.PaymentTicketResponse
import br.com.m2yconductorservices.data.remote.models.response.TicketPaymentResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface M2YCDTPaymentService {

    @POST("${M2YCDTNetworkConstants.CDT_PAYMENT_URL}getPayment")
    fun getPayment(@Body accountId: AccountIdIntRequest): Single<List<PaymentTicketResponse>>

    @POST("${M2YCDTNetworkConstants.CDT_PAYMENT_URL}pay")
    fun pay(@Body request: TicketPaymentRequest): Single<TicketPaymentResponse>

    @POST("${M2YCDTNetworkConstants.CDT_PAYMENT_URL}validate")
    fun validate(@Body barcode: ValidateBarCodeRequest?) : Single<BarcodeValidationResponse>
}