package br.com.m2yconductorservices.data.remote.models

import okhttp3.RequestBody
import java.io.Serializable

enum class DocumentType(private val docType: String) : Serializable {

    RG("rg"),
    CNH("cnh");
    fun toMap(): Map<String, RequestBody> {
        val map = mutableMapOf<String, RequestBody>()

        fun createPartFromString(descriptionString: String): RequestBody {
            return RequestBody.create(
                    okhttp3.MultipartBody.FORM, descriptionString)
        }

        fun addToMap(key: String, value: String?) {
            if (!value.isNullOrEmpty()) {
                map[key] = createPartFromString(value!!)
            }
        }

        addToMap("type", docType)
        return map
    }
}