package rip.deadcode.intellij.oda.formatter

import rip.deadcode.intellij.oda.model.*

object ThesaurusFormatter {

    fun format(thesaurus: Thesaurus): String {
        return if (thesaurus.results != null) {

            "<div>" + thesaurus.results
                    .flatMap { it.lexicalEntries }
                    .asSequence()
                    .map { format(it) }
                    .joinToString("") + "</div>"
        } else ""
    }

    fun format(thesaurusLexicalEntry: ThesaurusLexicalEntry): String {
        return if (thesaurusLexicalEntry.entries != null) {
            val entries = thesaurusLexicalEntry.entries.asSequence().map { format(it) }.joinToString("")
            "<div><span>[${thesaurusLexicalEntry.lexicalCategory}]</span>${entries}</div>"
        } else ""
    }

    fun format(thesaurusEntry: ThesaurusEntry): String {

        val variants = thesaurusEntry.variantForms
        val variantsResult = if (variants != null && variants.isNotEmpty()) {
            "<p>Variants: ${thesaurusEntry.variantForms.asSequence().map { SharedFormatter.format(it) }.joinToString(", ")}</p>"
        } else ""

        val senses = thesaurusEntry.senses
        val sensesResult = if (senses != null) {
            thesaurusEntry.senses.asSequence().map { format(it) }.joinToString("")
        } else ""

        return "<div>${sensesResult}${variantsResult}</div>"
    }

    fun format(thesaurusSense: ThesaurusSense, indent: Int = 0): String {

        val senses = if (thesaurusSense.synonyms != null) {
            thesaurusSense.synonyms
                    .asSequence()
                    .map { format(it) }
                    .joinToString("")
        } else ""

        val subsenses = if (thesaurusSense.subsenses != null && thesaurusSense.subsenses.isNotEmpty()) {
            "<div><h4>Subsenses</h4>" + thesaurusSense.subsenses.asSequence()
                    .map { format(it, indent + 1) }
                    .joinToString("") + "</div>"
        } else ""

        return senses + subsenses
    }

    fun format(synonym: SynonymAntonym): String {
        val region = if (synonym.regions != null && synonym.regions.isNotEmpty()) "<span>[" + synonym.regions.joinToString(",") + "]</span>" else ""
        val domain = if (synonym.domains != null && synonym.domains.isNotEmpty()) "<span>(" + synonym.domains.joinToString(",") + ")</span>" else ""
        return "<p>" + region + "<span>" + synonym.text + "</span>" + domain + "</p>"
    }
}
