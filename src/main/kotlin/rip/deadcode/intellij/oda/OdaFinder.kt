package rip.deadcode.intellij.oda

import com.google.api.client.http.GenericUrl
import com.google.api.client.http.HttpTransport
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.gson.Gson
import rip.deadcode.intellij.oda.model.Thesaurus
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets

object OdaFinder {

    private const val endpoint = "https://od-api.oxforddictionaries.com/api/v1"

    // TODO DI
    val defaultHttpTransport = NetHttpTransport()
    val gson = Gson()

    fun findEntries() {
        // TODO
    }

    fun findSynonyms(httpTransport: HttpTransport, gson: Gson, appId: String, appKey: String, word: String): Thesaurus? {

        if (word.isEmpty()) {
            return null
        }

        val result = httpTransport.createRequestFactory {
            it.headers["app_id"] = appId
            it.headers["app_key"] = appKey
        }.buildGetRequest(GenericUrl("${endpoint}/entries/en/${word}/synonyms"))
                .execute()
        return gson.fromJson(InputStreamReader(result.content, StandardCharsets.UTF_8), Thesaurus::class.java)
    }

    /**
     * Converts given word(spelling) to the word_id, described
     * [here](https://developer.oxforddictionaries.com/documentation/making-requests-to-the-api).
     * This method does NOT URL-encode.
     */
    fun toWordId(word: String): String =
            word.trim().toLowerCase().map { if (it.isWhitespace()) '_' else it }.joinToString(separator = "")

}
