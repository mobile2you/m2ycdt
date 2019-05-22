package br.com.m2yconductorservices.data.remote.models.request

data class CreateAddressRequest (
    var street: String?,
    var neighborhood: String?,
    var number: String?,
    var zip: String?,
    var city: String?,
    var state: String?,
    var complement: String?,
    var ref: String?,
    var country: String?
)