package rip.deadcode.intellij.oda

import rip.deadcode.intellij.dictionary.Dictionary
import rip.deadcode.intellij.oda.formatter.ThesaurusFormatter
import rip.deadcode.intellij.oda.model.Thesaurus

class OdaThesaurusDictionary : Dictionary {

    override fun canHandle(prefix: String): Boolean = prefix == "oxt"

    override fun getDisplayName(): String = "Oxford Dictionary API Thesaurus"

    override fun lookUp(word: String): String? {
        return OdaFinder.request(
                word,
                "${OdaFinder.endpoint}/thesaurus/en/${word}",
                Thesaurus::class.java,
                ThesaurusFormatter::format
        )
    }
}