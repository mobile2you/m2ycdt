package br.com.m2yconductorservices


object M2YCDTConstants {

    const val PACKAGE_NAME: String = BuildConfig.APPLICATION_ID

    //GENERAL
    const val LANGUAGE_PT = "pt"
    const val COUNTRY_BR = "BR"

    //DATE FORMATS
    const val VOUCHER_FORMAT = "dd 'de' MMMM 'de' yyyy"
    const val EXTRACT_FORMAT = "dd/MM/yyyy - HH:mm"
    const val COMMON_DATE_FORMAT = "dd/MM/yyyy"
    const val COMMON_DATE_TIME_FORMAT = "dd/MM/yyyy HH:mm"
    const val COMMON_TIME_FORMAT = "HH:mm"
    const val NOTIFICATION_TIME_FORMAT = "dd/MM/yyyy '\n' HH:mm"
    const val TICKET_DATE_FORMAT = "yyyy-MM-dd"
    const val COMMON_REGEX_DATE_FORMAT = "ddMMyy"
    const val COMMON_REGEX_DAY_MONTH_FORMAT = "ddMM"
    const val EXTRACT_DATE_FORMAT = "dd 'de' MMMM 'de' yyyy"
    const val DATE_AT_TIME_DATE_FORMAT = "dd/MM/yyyy 'às' HH:mm"
    const val CREATED_AT_DATE_FORMAT = "dd/MM/yyyy HH:mm"
    const val SYS_DATA_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss"
    const val FILTER_MONTHS_DATE_FORMAT = "MMMM/yyyy"
    const val CDT_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS"
    const val RECHARGE_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'+'SSS" //2018-12-20T16:06:16.355+0000
    const val PLACEHOLDER_FORMAT = "yyyyMMddHHmmss"
    const val EXTRAC_REQUEST_DATE_FORMAT = "yyyy-MM-dd"
    const val RECEIPT_DATE_FORMAT = "dd/MM/yyyy HH:mm"
    const val TICKET_RECEIPT_DATE_FORMAT = "dd 'de' MMMM 'de' yyyy"
    const val CDT_TICKET_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS"
    const val CDT_TICKET_RESALES_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ"
    const val PAYMENT_DUEDATE = "dd/mm/yyyy'T'HH:mm:ss.SSS"
}