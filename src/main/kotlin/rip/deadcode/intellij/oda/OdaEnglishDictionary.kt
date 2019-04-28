package rip.deadcode.intellij.oda

import rip.deadcode.intellij.dictionary.Dictionary
import rip.deadcode.intellij.oda.formatter.EntryFormatter
import rip.deadcode.intellij.oda.model.RetrieveEntry

class OdaEnglishDictionary : Dictionary {

    override fun canHandle(prefix: String): Boolean = prefix == "oxe"

    override fun getDisplayName(): String = "Oxford Dictionary API Definitions"

    override fun lookUp(word: String): String? {
        return OdaFinder.request(
                word,
                "${OdaFinder.endpoint}/entries/en-us/${word}",  // TODO locale
                RetrieveEntry::class.java,
                EntryFormatter::format
        )
    }
}
