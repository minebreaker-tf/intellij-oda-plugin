package rip.deadcode.intellij.oda

import rip.deadcode.intellij.dictionary.Dictionary
import rip.deadcode.intellij.oda.model.ThesaurusFormatter
import rip.deadcode.intellij.oda.utils.OdaPasswords

class OdaThesaurusDictionary : Dictionary {

    override fun canHandle(prefix: String): Boolean = prefix == "otd"

    override fun getDisplayName(): String = "Oxford Dictionary API Thesaurus"

    override fun lookUp(word: String): String? {

        val id = OdaPasswords.getId()
        val key = OdaPasswords.getKey()

        if (id == null || key == null) {
            return "<div>Oxford API AppID/AppKey is not set.</div>"
        }

        val result = OdaFinder.findSynonyms(OdaFinder.defaultHttpTransport, OdaFinder.gson, id, key, word)
        return if (result != null) {
            ThesaurusFormatter.format(result)
        } else null
    }
}