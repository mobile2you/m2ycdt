package br.com.m2yconductorservices.data.remote.models.response

data class FirstAccessResponse(
        val msg: String?,
        val first_access: Boolean?,
        val birth_date: String?,
        val account_ids: List<AccountsIds>?,
        val name: String?,
        val email: String?,
        val phone: PhoneModel?
)

data class PhoneModel(
        val id: String,
        val idTipotelefone: String,
        val idPessoa: String,
        val ddd: String,
        val telefone: String,
        val ramal : String,
        val status: Int
) {
    fun generatePhoneStringWithCountry(): String{
        val newDdd = ddd.takeLast(2)
        return "$newDdd$telefone"
    }

    fun generatePhoneString(): String{
        val newDdd = ddd.takeLast(2)
        return "$newDdd$telefone"
    }
}

data class AccountsIds(
        var id: String?
)