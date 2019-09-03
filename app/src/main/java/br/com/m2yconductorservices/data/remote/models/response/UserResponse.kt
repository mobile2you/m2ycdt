package br.com.m2yconductorservices.data.remote.models.response

data class UserResponse(
    val id: String?,
    var created_at: String?,
    var picture: String?,
    var name: String?,
    val cpf: String?,
    var temp_pass: Boolean?,
    var account_id: String?,
    var card_id: String?,
    var saudation: String?,
    var person_id: String?,
    var activated: Boolean?,
    var document: String?,
    var step: String?,
    var re: String?,
    var phone: String?,
    var blocked: Boolean,
    var birth_date: String,
    var urls: LoginUrlResponse?,
    var email: String?,
    var token: String,
    var has_cards: Boolean,
    var plan: String?
)

data class LoginUrlResponse(
    val terms: String?,
    val policy: String?,
    val about: String?
)

data class FirstAccessTerms(
    val terms_url: String?,
    val politics_url: String?,
    val about: String?
)

fun UserResponse.getFirstName() = name?.split(" ")?.first() ?: ""