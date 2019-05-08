package br.com.m2yconductorservices.data.repositories

import br.com.m2yconductorservices.data.remote.datasources.M2YCDTInvoicesDataSource

object M2YCDTInvoicesRepository {

    fun getInvoices() = M2YCDTInvoicesDataSource.getInvoices()
}