package br.com.m2yconductorservices.utils

import java.nio.charset.Charset

object M2YCDTBase64Helper {
    @JvmStatic()
    fun encrypt(textToEncrypt: String): String {
        return android.util.Base64.encodeToString(textToEncrypt.toByteArray(Charsets.UTF_8), android.util.Base64.NO_WRAP).toString()
    }

    @JvmStatic()
    fun descrypt(textToDecrypt: String): String {
        return String(android.util.Base64.decode(textToDecrypt, android.util.Base64.NO_WRAP), Charsets.UTF_8)
    }
}