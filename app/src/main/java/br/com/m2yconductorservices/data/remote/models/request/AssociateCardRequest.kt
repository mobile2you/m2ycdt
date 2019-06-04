package br.com.m2yconductorservices.data.remote.models.request

data class AssociateCardRequest (
    var cpf: String?,
    var cardNumber: String?,
    var name: String?,
    var address: AddressModel?
)

data class AddressModel(
    var zip: String = "",
    var number: String = "",
    var street: String = "",
    var complement: String = "",
    var city: String = "",
    var state: String = "",
    var neighborhood: String = "",
    var ref: String = "",
    var country: String = ""
) {
    fun formatedStreet() = if (complement.isNotBlank()) "$street, $number, $complement" else "$street, $number"
    fun formatedCity() = "$city - $state"
}