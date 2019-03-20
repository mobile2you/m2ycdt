package br.com.m2yconductorservices.data

import java.lang.NullPointerException

object M2YCDTEnvironment {

    private lateinit var ENVIRONMENT_BASE_URL: String
    val baseUrl: String
        get() = try {
            ENVIRONMENT_BASE_URL
        }  catch (e: Exception) {
            throw NullPointerException("M2YCDTEnvironment not initialized")
        }

    private lateinit var ENVIRONMENT_BASE_APPLICATION_HEADER: String
    val applicationHeader: String
        get() = try {
            ENVIRONMENT_BASE_APPLICATION_HEADER
        }  catch (e: Exception) {
            throw NullPointerException("M2YCDTEnvironment not initialized")
        }

    private var DEBUG: Boolean = false
    val debug: Boolean
        get() = DEBUG

    private var REPORT_CRASH: Boolean = false
    val reportCrash: Boolean
        get() = REPORT_CRASH

    fun init(baseUrl: String, applicationHeader: String, debug: Boolean, reportCrash: Boolean) {
        ENVIRONMENT_BASE_URL = baseUrl
        ENVIRONMENT_BASE_APPLICATION_HEADER = applicationHeader
        DEBUG = debug
        REPORT_CRASH = reportCrash
    }
}