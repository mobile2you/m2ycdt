package br.com.m2yconductorservices.data.remote.models.response

class ValidateCardPasswordResponse(
        val mensagem: String,
        val idStatusCartao: Int,
        val statusCartao: String,
        val quantidadeTentativas: Int,
        val qualtidadeMaximaTentativas: Int
) {
    fun toParsedModel(): ParsedValidateCardPasswordResponse {
        val cardStatus = CardStatus.values().find { it.description == statusCartao }
        return ParsedValidateCardPasswordResponse(
                mensagem,
                cardStatus ?: CardStatus.Ok,
                quantidadeTentativas,
                quantidadeTentativas)
    }
}

data class ParsedValidateCardPasswordResponse(
        val message: String,
        val cardStatus: CardStatus,
        val attemptQuantity: Int,
        val maximumAttemptQuantity: Int
) {

}

enum class CardStatus(val cardStatusId: Int, val description: String) {
    Ok(0, "Ok"),
    Blocked(2, "Bloqueado")
}