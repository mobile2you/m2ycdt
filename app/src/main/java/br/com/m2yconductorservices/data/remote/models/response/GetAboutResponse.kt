package br.com.m2yconductorservices.data.remote.models.response


data class GetAboutResponse (
        val id: String? = null,
        val createdAt: String? = null,
        val picture: String? = null,
        val name: String? = null,
        val cpf: String? = null,
        val cnpj: String? = "",
        val social_reason: String? = "",
        val fantasy_name: String? = "",
        val partner_name: String? = "",
        val partner_cpf: String? = "",
        val company_date: String? = "",
        val email: String? = null,
        val phone: String? = null,
        val address: Address? = null,
        val mailingAddress: Address? = null
)

data class Address (
        val id: String? = null,
        val street: String? = null,
        val neighborhood: String? = null,
        val number: String? = null,
        val zip: String? = null,
        val city: String? = null,
        val state: String? = null,
        val country: String? = null,
        val ref: String? = null,
        val complement: String? = null,
        val isMailing: Boolean? = null
)
