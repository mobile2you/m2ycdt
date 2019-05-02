package br.com.m2yconductorservices.data.remote


import android.util.Base64
import br.com.m2yconductorservices.M2YCDTNetworkConstants
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

object M2YCDTEncryptHelper {

    private val charset = Charsets.UTF_8
    private val iv = M2YCDTNetworkConstants.ENCRYPTION_IV.toByteArray(charset)
    private val key = SecretKeySpec(M2YCDTNetworkConstants.ENCRYPTION_KEY.toByteArray(charset), "AES")
    private const val transformationMethod = "AES/CBC/PKCS7Padding"

    fun encrypt(string: String?): String {
        val message = string?.toByteArray(charset)
        val cipher = Cipher.getInstance(transformationMethod)
        val ivSpec = IvParameterSpec(iv)
        cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec)
        val cipherText = cipher.doFinal(message)
        return Base64.encodeToString(cipherText, Base64.NO_WRAP)
    }

    fun decrypt(string: String): String {
        val decodedCipherText = Base64.decode(string, Base64.NO_WRAP)
        val cipher = Cipher.getInstance(transformationMethod)
        val ivSpec = IvParameterSpec(iv)
        cipher.init(Cipher.DECRYPT_MODE, key, ivSpec)
        val decryptedBytes = cipher.doFinal(decodedCipherText, 0, decodedCipherText.size)
        return String(decryptedBytes, charset)
    }
}