package br.com.m2yconductorservices.utils.extensions

import br.com.m2yconductorservices.M2YCDTConstants
import java.text.NumberFormat
import java.util.*

/**
 * Created by azul on 11/10/17.
 */

/**
 * @param locale locale of the currency to m2yCdtFormat
 * @return the value with the locale currency
 */
fun Float.m2yCdtFormatToCurrency(locale: Locale): String {
    val currencyFormatter = NumberFormat.getCurrencyInstance(locale)
    return currencyFormatter.format(this.toDouble())
}

/**
 * @return the value with the BRL currency
 */
fun Float.m2yCdtFormatCurrencyBRL(trimRs: Boolean = false): String {
    val locale = Locale(M2YCDTConstants.LANGUAGE_PT, M2YCDTConstants.COUNTRY_BR)
    return if (trimRs) this.m2yCdtFormatToCurrency(locale).replace("R$", "") else this.m2yCdtFormatToCurrency(locale)
}

fun String.m2yCdtFillWithChars(minLenght: Int, char: Char, position: CustomString): String {
    if (this.length >= minLenght)
        return this

    val diff = minLenght - this.length
    val insert = (0 until diff).map { char }.joinToString("")

    return when (position) {
        CustomString.BEFORE -> {
            "$insert$this"
        }
        CustomString.AFTER -> {
            "$this$insert"
        }
        else -> {
            ""
        }
    }

}

fun String.m2yCdtUnmask() = replace("[.]".toRegex(), "").replace("[-]".toRegex(), "")
    .replace("[/]".toRegex(), "").replace("[(]".toRegex(), "")
    .replace("[)]".toRegex(), "").replace(" ".toRegex(), "")
    .replace("[:]".toRegex(), "").replace("[+]".toRegex(), "")
    .replace("[,]".toRegex(), "")

enum class CustomString {
    BEFORE, AFTER, MID
}

fun String.m2yCdtNormalizeSpaces() = this.trim().replace("\\s{2,}".toRegex(), " ")
