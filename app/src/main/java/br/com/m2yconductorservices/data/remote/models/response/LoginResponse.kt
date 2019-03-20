package br.com.m2yconductorservices.data.remote.models.response

data class LoginResponse(
        val id: String,
        val created_at: String,
        val picture: String,
        val name: String,
        val cpf: String,
        val temp_pass: Boolean,
        val account_id: String,
        val card_id: String,
        val saudation: String,
        val re: String?,
        val phone: String?,
        val blocked: Boolean,
        val person_id: String,
        val step: String?,
        val activated: Boolean?,
        val birth_date: String
) {
    fun getCurrentStep() = RegisterStep.values().firstOrNull { it.stepName == step }
}

enum class RegisterStep(val stepName: String) {
    SMS("sms"),
    SELFIE("selfie"),
    FRONT_DOC("document_front"),
    BACK_DOC("document_back"),
    PASSWORD("password");

//    fun getStepIntent(context: Context, phone: String? = null): Intent {
//        return when (this) {
//            RegisterStep.SMS -> context.createPersonalDataCodeValidationIntent(phone, true)
//            RegisterStep.SELFIE -> startActivitySlideTransition(createPersonalDataDocumentTipsIntent(false))
//            RegisterStep.FRONT_DOC -> startActivitySlideTransition(createPersonalDataDocumentTipsIntent(true))
//            RegisterStep.BACK_DOC -> startActivitySlideTransition(createPersonalDataDocumentTipsIntent(true))
//            RegisterStep.PASSWORD -> context.createCreditCardConfirmPasswordIntent(CreditCardNewPasswordOrigin.REGISTER)
//            else -> context.createCreditCardConfirmPasswordIntent(CreditCardNewPasswordOrigin.REGISTER)
//        }
//    }
}