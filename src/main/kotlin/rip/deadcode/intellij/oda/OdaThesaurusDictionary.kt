package rip.deadcode.intellij.oda

import com.google.api.client.http.HttpResponseException
import com.google.common.base.Strings.isNullOrEmpty
import rip.deadcode.intellij.dictionary.Dictionary
import rip.deadcode.intellij.oda.model.ThesaurusFormatter
import rip.deadcode.intellij.oda.utils.OdaPasswords
import java.io.IOException

class OdaThesaurusDictionary : Dictionary {

    override fun canHandle(prefix: String): Boolean = prefix == "otd"

    override fun getDisplayName(): String = "Oxford Dictionary API Thesaurus"

    override fun lookUp(word: String): String? {

        val id = OdaPasswords.getId()
        val key = OdaPasswords.getKey()

        if (isNullOrEmpty(id) || isNullOrEmpty(key)) {
            return "<div>Oxford API AppID/AppKey is not set.</div>"
        }

        return try {
            val result = OdaFinder.findSynonyms(OdaFinder.defaultHttpTransport, OdaFinder.gson, id!!, key!!, word)
            if (result != null) {
                ThesaurusFormatter.format(result)
            } else null
        } catch (e: HttpResponseException) {
            when (e.statusCode) {
                400 -> "<p>400 Bad Request. This may be a plugin bug.</p>"
                403 -> "<p>403 Authentication failed. Your Oxford Dictionaries API AppID/AppKey may be wrong.</p>"
                404 -> null
                500, 502, 503, 504 -> "<p>Server error. Oxford Dictionaries API is down.</p>"
                else -> "<p>Unexpected HTTP error.</p>"
            }

        } catch (e: IOException) {
            "<p>Failed to connect. The server seems to be down.</p>"
        }
    }
}