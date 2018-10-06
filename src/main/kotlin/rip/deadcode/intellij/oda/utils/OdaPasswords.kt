package rip.deadcode.intellij.oda.utils

import com.intellij.credentialStore.CredentialAttributes
import com.intellij.credentialStore.generateServiceName
import com.intellij.ide.passwordSafe.PasswordSafe

object OdaPasswords {

    private val credAttrId = CredentialAttributes(generateServiceName("OxfordDictionaryAPI", "api_id"))
    private val credAttrKey = CredentialAttributes(generateServiceName("OxfordDictionaryAPI", "api_key"))

    fun getId() = PasswordSafe.instance.getPassword(credAttrId)
    fun getKey() = PasswordSafe.instance.getPassword(credAttrKey)

    fun set(id: String, key: String) {
        PasswordSafe.instance.setPassword(credAttrId, id)
        PasswordSafe.instance.setPassword(credAttrKey, key)
    }
}
