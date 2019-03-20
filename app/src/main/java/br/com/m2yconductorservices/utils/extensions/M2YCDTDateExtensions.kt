package br.com.m2yconductorservices.utils.extensions

import br.com.m2yconductorservices.M2YCDTConstants
import java.text.SimpleDateFormat
import java.util.*



/**
 * Created by joaopaulo on 19/10/17.
 */
private const val SECOND_MILLIS = 1000f
private const val MINUTE_MILLIS = 60 * SECOND_MILLIS
private const val HOUR_MILLIS = 60 * MINUTE_MILLIS
private const val DAY_MILLIS = 24 * HOUR_MILLIS
private const val MONTH_MILLIS = 30 * DAY_MILLIS

fun String.m2yCdtChangeDateFormat(currentDateFormat: String, newDateFormat: String,
                                  locale: Locale = Locale(M2YCDTConstants.LANGUAGE_PT, M2YCDTConstants.COUNTRY_BR), isUTC: Boolean = false): String {
    return try {
        val oldDf = SimpleDateFormat(currentDateFormat)
        val date = oldDf.parse(this)
        val newDf = SimpleDateFormat(newDateFormat)

        if (isUTC)
            newDf.timeZone = TimeZone.getTimeZone("gmt")

        newDf.format(date)
    } catch (e: Exception) {
        e.printStackTrace()
        this
    }
}


fun String.m2yCdtDateFromString(dateFormat: String, locale: Locale = Locale(M2YCDTConstants.LANGUAGE_PT, M2YCDTConstants.COUNTRY_BR),
                                isUTC: Boolean = false): Date? {
    return try {
        val date = if (isUTC) m2yCdtChangeDateFormat(dateFormat, dateFormat, isUTC = true) else this
        val sdf = SimpleDateFormat(dateFormat, locale)
        sdf.parse(date)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

fun Calendar.m2yCdtFormat(dateFormat: String, locale: Locale = Locale(M2YCDTConstants.LANGUAGE_PT, M2YCDTConstants.COUNTRY_BR)): String {
    val sdf = SimpleDateFormat(dateFormat, locale)
    return sdf.format(this.time)
}