package br.com.m2yconductorservices.data.repositories

import br.com.m2yconductorservices.data.remote.datasources.M2YCDTBankSlipRemoteDataSource
import br.com.m2yconductorservices.data.remote.models.request.IdIntRequest
import okio.Okio
import java.io.File

object M2YCDTBankSlipRepository {

    fun getPdf(ticketId: IdIntRequest?, storageDir: File) = getPdf(ticketId, storageDir, ticketId?.id.toString())


    fun getPdf(ticketId: IdIntRequest?, storageDir: File, fileName: String) = M2YCDTBankSlipRemoteDataSource.getPDF(ticketId)
    .map {
        val file = File.createTempFile(
            fileName, /* prefix */
            ".pdf", /* suffix */
            storageDir      /* directory */
        )
        val sink = Okio.buffer(Okio.sink(file))
        // you can access body of response
        sink.writeAll(it.body().source())
        sink.close()
        file
    }
}