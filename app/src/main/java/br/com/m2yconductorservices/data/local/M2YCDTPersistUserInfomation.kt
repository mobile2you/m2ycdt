package br.com.m2yconductorservices.data.local

import android.os.SystemClock
import br.com.m2yconductorservices.data.remote.models.response.*
import br.com.m2yconductorservices.utils.M2YCDTBase64Helper

object M2YCDTPersistUserInformation {
    private var persistedUser: UserResponse? = null
    private var cdtTokenResponse = TokenCDTResponse("", -1, "")
    private var cdtTokenSystemTime: Long = -1
    private var userAccountString: String? = null
    private var userLogin: String? = null
    private var userAccounts: AccountPageResponse? = null
    private var password: String = ""
    private var firstTime: Boolean = false
    private var listAccounts: List<AccountResponse>? = null
    private var phone: String? = null

    fun resetUser() {
        persistedUser = null
        cdtTokenResponse = TokenCDTResponse("", 0, "")
        userLogin("")
        password("")
    }

    fun setUser(user: UserResponse) {
        this.persistedUser = user
        this.userLogin = user.cpf
        M2YCDTPreferencesHelper.userCpf = user.cpf
        M2YCDTPreferencesHelper.userName = user.getFirstName()
    }

    fun setAccounts(listAccounts: List<AccountResponse>) {
        this.listAccounts = listAccounts
    }

    fun getAccounts(): List<AccountResponse> = listAccounts ?: emptyList()

    fun id(): String = persistedUser?.id ?: ""
    fun cpf(): String = persistedUser?.cpf ?: M2YCDTPreferencesHelper.userCpf ?: ""

    fun createdAt(): String = persistedUser?.created_at ?: ""
    fun createdAt(newCreatedAt: String): String {
        persistedUser?.created_at = newCreatedAt
        return createdAt()
    }

    fun name(): String = persistedUser?.name ?: M2YCDTPreferencesHelper.userName ?: ""

    fun accountId(): String = persistedUser?.account_id ?: ""
    fun accountId(newAccountId: String): String {
        persistedUser?.account_id = newAccountId
        return accountId()
    }

    fun picture(): String = persistedUser?.picture ?: ""
    fun picture(newPicture: String): String {
        persistedUser?.picture = newPicture
        return picture()
    }

    fun tempPass(): Boolean? = persistedUser?.temp_pass
    fun tempPass(newTempPass: Boolean): Boolean? {
        persistedUser?.temp_pass = newTempPass
        return tempPass()
    }


    fun cardId(): String = persistedUser?.card_id ?: ""
    fun cardId(newCardId: String): String {
        persistedUser?.card_id = newCardId
        return cardId()
    }

    fun saudation(): String = persistedUser?.saudation ?: ""
    fun saudation(newSaudation: String): String {
        persistedUser?.saudation = newSaudation
        return saudation()
    }

    fun personId(): String = persistedUser?.person_id ?: ""
    fun personId(newPersonId: String): String {
        persistedUser?.person_id = newPersonId
        return personId()
    }

    fun activated(): Boolean? = persistedUser?.activated
    fun activated(newActivated: Boolean): Boolean? {
        persistedUser?.activated = newActivated
        return activated()
    }

    fun document(): String = persistedUser?.document ?: ""
    fun document(newDocument: String): String {
        persistedUser?.document = newDocument
        return document()
    }

    fun step(): String = persistedUser?.step ?: ""
    fun step(newStep: String): String {
        persistedUser?.step = newStep
        return step()
    }

    fun re(): String = persistedUser?.re ?: ""
    fun re(newRe: String): String {
        persistedUser?.re = newRe
        return re()
    }

    fun phone(): String = persistedUser?.phone ?: phone ?: ""
    fun phone(newPhone: String): String {
        persistedUser?.phone = newPhone
        this.phone = newPhone
        return phone()
    }

    fun blocked(): Boolean = persistedUser?.blocked ?: false
    fun blocked(newBlocked: Boolean): Boolean {
        persistedUser?.blocked = newBlocked
        return blocked()
    }

    fun birthDate(): String = persistedUser?.birth_date ?: ""
    fun birthDate(newBirthDate: Boolean): String {
        persistedUser?.blocked = newBirthDate
        return birthDate()
    }

    fun urls(): LoginUrlResponse = persistedUser?.urls ?: LoginUrlResponse("", "", "")
    fun urls(newUrls: LoginUrlResponse?): LoginUrlResponse {
        newUrls?.let { persistedUser?.urls = it }
        return urls()
    }

    fun email(): String = persistedUser?.email ?: ""
    fun email(newEmail: String): String {
        persistedUser?.email = newEmail
        return email()
    }

    fun token(): String = persistedUser?.token ?: ""
    fun token(newToken: String): String {
        persistedUser?.token = newToken
        return token()
    }

    fun userAccountString(): String? = userAccountString
    fun userAccountString(newUserAccountId: String): String? {
        userAccountString = newUserAccountId
        return userAccountString()
    }

    fun userLogin(): String =
        if (!userLogin.isNullOrEmpty()) userLogin!! else M2YCDTPreferencesHelper.userCpf ?: ""

    fun userLogin(newUserLogin: String): String {
        userLogin = newUserLogin
        return userLogin()
    }

    fun hasLoginSaved(): Boolean = !userLogin().isEmpty()

    fun userAccounts(): AccountPageResponse? = userAccounts
    fun userAccounts(newUserAccounts: AccountPageResponse?): AccountPageResponse? {
        userAccounts = newUserAccounts
        return newUserAccounts
    }

    fun password(): String =
        if (!password.isEmpty()) password else M2YCDTBase64Helper.descrypt(
            M2YCDTPreferencesHelper.userPassword ?: ""
        )

    fun password(newPassword: String): String {
        password = newPassword
        M2YCDTPreferencesHelper.userPassword = newPassword
        return password()
    }

    fun firstTime(): Boolean = firstTime
    fun firstTime(newFirstTime: Boolean): Boolean {
        firstTime = newFirstTime
        return firstTime()
    }

    fun cdtAccessToken(): String = cdtTokenResponse.access_token
    fun cdtAccessToken(newAccessToken: String): String {
        cdtTokenResponse.access_token = newAccessToken
        return cdtAccessToken()
    }

    fun cdtExpiresIn(): Int = cdtTokenResponse.expires_in
    fun cdtExpiresIn(newExpiresIn: Int): Int {
        cdtTokenResponse.expires_in = newExpiresIn
        return cdtExpiresIn()
    }

    fun cdtTokenType(): String = cdtTokenResponse.token_type
    fun cdtTokenType(newCdtTokenType: String): String {
        cdtTokenResponse.token_type = newCdtTokenType
        return cdtTokenType()
    }

    fun cdtTokenSystemTime(): Long = cdtTokenSystemTime
    fun cdtTokenSystemTime(newCdtTokenSystemTime: Long): Long {
        cdtTokenSystemTime = newCdtTokenSystemTime
        return cdtTokenSystemTime()
    }

    fun hasCards() = persistedUser?.has_cards == true
    fun hasCards(hasCards: Boolean): Boolean {
        persistedUser?.has_cards = hasCards
        return persistedUser?.has_cards == true
    }

    fun isCdtTokenInvalid(): Boolean {
        return (SystemClock.elapsedRealtime() - cdtTokenSystemTime()) > cdtExpiresIn()
    }

    fun clear() {
        persistedUser = null
        cdtTokenResponse = TokenCDTResponse("", -1, "")
        cdtTokenSystemTime = -1
        userAccountString = null
        userLogin = null
        userAccounts = null
        password = ""
        firstTime = false
        listAccounts = null
    }

}