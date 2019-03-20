package br.com.m2yconductorservices.data.repositories

import br.com.m2yconductorservices.M2YCDTConstants
import br.com.m2yconductorservices.data.local.M2YCDTPersistUserInformation
import br.com.m2yconductorservices.data.local.models.PaymentModel
import br.com.m2yconductorservices.data.local.models.toPaymentModel
import br.com.m2yconductorservices.data.remote.datasources.M2YCDTPaymentRemoteDataSource
import br.com.m2yconductorservices.data.remote.models.request.AccountIdIntRequest
import br.com.m2yconductorservices.data.remote.models.request.ValidateBarCodeRequest
import br.com.m2yconductorservices.data.remote.models.request.toPaymentRequest
import br.com.m2yconductorservices.data.remote.models.response.TicketPaymentResponse
import br.com.m2yconductorservices.utils.extensions.m2yCdtChangeDateFormat
import io.reactivex.Single

object M2YCDTPaymentRepository {

    fun getPayment(accountId: AccountIdIntRequest) = M2YCDTPaymentRemoteDataSource.getPayment(accountId)

    fun pay(paymentModel: PaymentModel): Single<TicketPaymentResponse> {
        val paymentRequest = paymentModel.toPaymentRequest(M2YCDTAccountRepository.accountId.toLong(),
                M2YCDTPersistUserInformation.name())
        paymentRequest.dueDate = paymentRequest.dueDate.m2yCdtChangeDateFormat(M2YCDTConstants.PAYMENT_DUEDATE, M2YCDTConstants.CDT_TICKET_RESALES_DATE_FORMAT)
        return M2YCDTPaymentRemoteDataSource.pay(paymentRequest)
    }

    fun validate(barcode: ValidateBarCodeRequest?) = M2YCDTPaymentRemoteDataSource.validate(barcode).map { it.toPaymentModel() }
}