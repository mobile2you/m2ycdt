package br.com.m2yconductorservices.data.remote.models.response

import com.google.gson.Gson
import java.io.Serializable

class Transferp2pResponse(var id: Int?,
                          var transactionCode: String?,
                          var originalAccount: Float?,
                          var destinationAccount: Float?,
                          var amount: Float?,
                          var date: String?,
                          var name: String?,
                          var description: String?,
                          var idAdjustment: Float,
                          var idIssuer: Float?,
                          var tpAdjustment: String?,
                          var transactionTariffCode: String?) : Serializable {

    val username: String
        get() = if (description?.contains("@@") == true)
            description?.split("@@")?.first() ?: ""
        else
            ""

    val displayDescription: String
        get() = if (description?.contains("@@") == true)
            description?.split("@@")?.last() ?: ""
        else
            ""

    val jsonObject: Transferp2pResponseDescription?
    get() {
        return try {
            Gson().fromJson(description, Transferp2pResponseDescription::class.java)
        } catch (ex: Exception) {
            null
        }
    }
}

class Transferp2pResponseDescription(
    val name: String,
    val bank: String,
    val cpfOrCNPJ: String
)

