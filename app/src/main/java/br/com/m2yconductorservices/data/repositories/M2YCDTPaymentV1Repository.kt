package br.com.m2yconductorservices.data.repositories

import br.com.m2yconductorservices.M2YCDTConstants
import br.com.m2yconductorservices.data.local.M2YCDTPersistUserInformation
import br.com.m2yconductorservices.data.local.models.PaymentModel
import br.com.m2yconductorservices.data.remote.datasources.M2YCDTPaymentRemoteDataSource
import br.com.m2yconductorservices.data.remote.datasources.M2YCDTPaymentV1RemoteDataSource
import br.com.m2yconductorservices.data.remote.models.request.AccountIdIntRequest
import br.com.m2yconductorservices.data.remote.models.request.toPaymentV1Request
import br.com.m2yconductorservices.data.remote.models.response.TicketPaymentV1Response
import br.com.m2yconductorservices.utils.extensions.m2yCdtChangeDateFormat
import io.reactivex.Single

object M2YCDTPaymentV1Repository {

    fun getPayment(accountId: AccountIdIntRequest) = M2YCDTPaymentV1RemoteDataSource.getPayment(accountId)

    fun pay(paymentModel: PaymentModel): Single<TicketPaymentV1Response> {
        val paymentRequest = paymentModel.toPaymentV1Request(M2YCDTAccountRepository.accountId.toLong(),
                M2YCDTPersistUserInformation.name())
        paymentRequest.dueDate = paymentRequest.dueDate.m2yCdtChangeDateFormat(M2YCDTConstants.PAYMENT_DUEDATE, M2YCDTConstants.CDT_TICKET_RESALES_DATE_FORMAT)
        return M2YCDTPaymentV1RemoteDataSource.pay(paymentRequest)
    }
}