package rip.deadcode.intellij.oda.formatter

import rip.deadcode.intellij.oda.model.Entry
import rip.deadcode.intellij.oda.model.LexicalEntry
import rip.deadcode.intellij.oda.model.RetrieveEntry
import rip.deadcode.intellij.oda.model.Sense

object EntryFormatter {

    fun format(retrieveEntry: RetrieveEntry): String {

        return if (retrieveEntry.results != null) {
            "<div>" + retrieveEntry.results
                    .flatMap { it.lexicalEntries }
                    .asSequence()
                    .map { format(it) }
                    .joinToString("") + "</div>"
        } else ""
    }

    fun format(lexicalEntry: LexicalEntry): String {
        return if (lexicalEntry.entries != null) {
            val entries = lexicalEntry.entries.asSequence().map { format(it) }.joinToString("")
            val ref = if (lexicalEntry.derivativeOf != null) {
                "<p>See: " + lexicalEntry.derivativeOf.asSequence().map { it.text }.joinToString(", ") + "</p>"
            } else ""
            "<div><p>[${lexicalEntry.lexicalCategory}]</p>${entries}${ref}</div>"
        } else ""
    }

    fun format(entry: Entry): String {

        val variants = entry.variantForms
        val variantsResult = if (variants != null && variants.isNotEmpty()) {
            "<p>Variants: ${entry.variantForms.asSequence().map { SharedFormatter.format(it) }.joinToString(", ")}</p>"
        } else ""

        val senses = entry.senses
        val sensesResult = if (senses != null) {
            entry.senses.asSequence().map { format(it) }.joinToString("")
        } else ""

        return "<div>${sensesResult}${variantsResult}</div>"
    }

    fun format(sense: Sense, indent: Int = 0): String {

        val region = if (sense.regions != null && sense.regions.isNotEmpty()) "<span>[" + sense.regions.joinToString(",") + "]</span>" else ""
        val definitions = if (sense.definitions != null && sense.definitions.isNotEmpty()) {
            "<span>" + sense.definitions.joinToString(",") + "</span>"
        } else ""
        val domain = if (sense.domains != null && sense.domains.isNotEmpty()) "<span>(" + sense.domains.joinToString(",") + ")</span>" else ""

        val subsenses = if (sense.subsenses != null && sense.subsenses.isNotEmpty()) {
            "<div><h4>Subsenses</h4>" + sense.subsenses.asSequence()
                    .map { format(it, indent + 1) }
                    .joinToString("") + "</div>"
        } else ""

        return "<p>${region}${definitions}${domain}</p>" + subsenses
    }
}
