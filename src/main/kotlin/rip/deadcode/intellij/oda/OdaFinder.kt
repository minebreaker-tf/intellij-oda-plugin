package rip.deadcode.intellij.oda

import com.google.api.client.http.GenericUrl
import com.google.api.client.http.HttpResponseException
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.common.base.Strings
import com.google.gson.Gson
import com.google.gson.JsonParseException
import rip.deadcode.intellij.oda.utils.OdaPasswords
import java.io.IOException
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets

object OdaFinder {

    const val endpoint = "https://od-api.oxforddictionaries.com/api/v2"

    // TODO DI
    private val defaultHttpTransport = NetHttpTransport()
    private val gson = Gson()

    fun <T> request(word: String, url: String, entityClass: Class<T>, format: (T) -> String): String? {

        val appId = OdaPasswords.getId()
        val appKey = OdaPasswords.getKey()

        if (Strings.isNullOrEmpty(appId) || Strings.isNullOrEmpty(appKey)) {
            return "<div>Oxford API AppID/AppKey is not set.</div>"
        }

        if (word.isBlank()) {
            return null
        }

        return try {

            val result = defaultHttpTransport.createRequestFactory {
                it.connectTimeout = 1000
                it.readTimeout = 3000
                it.headers["app_id"] = appId
                it.headers["app_key"] = appKey
            }.buildGetRequest(GenericUrl(url))
                    .execute()
            val resultEntity = gson.fromJson(InputStreamReader(result.content, StandardCharsets.UTF_8), entityClass)
            if (resultEntity != null) {
                format(resultEntity)
            } else null
        } catch (e: HttpResponseException) {
            when (e.statusCode) {
                400 -> "<p>400 Bad Request. This may be a plugin bug.</p>"
                403 -> "<p>403 Authentication failed. Your Oxford Dictionaries API AppID/AppKey may be wrong, or you reached the API usage limit.</p>"
                404 -> null
                500, 502, 503, 504 -> "<p>Server error. Oxford Dictionaries API seems to be down.</p>"
                else -> "<p>Unexpected HTTP error.<br/>${e.statusCode} ${e.statusMessage}</p>"
            }

        } catch (e: IOException) {
            "<p>Failed to connect. The server seems to be down.</p>"
        } catch (e: JsonParseException) {
            "<p>Failed to parse json. This may be a plugin bug.</p>"
        }
    }

    /**
     * Converts given word(spelling) to the word_id, described
     * [here](https://developer.oxforddictionaries.com/documentation/making-requests-to-the-api).
     * This method does NOT URL-encode.
     */
    fun toWordId(word: String): String =
            word.trim().toLowerCase().map { if (it.isWhitespace()) '_' else it }.joinToString(separator = "")

}
