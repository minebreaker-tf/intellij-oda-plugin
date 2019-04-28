package rip.deadcode.intellij.oda.formatter

import rip.deadcode.intellij.oda.model.Domain
import rip.deadcode.intellij.oda.model.LexicalCategory
import rip.deadcode.intellij.oda.model.Region
import rip.deadcode.intellij.oda.model.VariantForm

object SharedFormatter {

    fun format(domain: Domain): String {
        return domain.text
    }

    fun format(lexicalCategory: LexicalCategory): String {
        return lexicalCategory.text
    }

    fun format(region: Region): String {
        return region.text
    }

    fun format(variantForm: VariantForm): String {
        val regions = if (variantForm.regions != null) {
            "[" + variantForm.regions.joinToString(", ") { format(it) } + "] "
        } else ""
        return """${regions}${variantForm.text}"""
    }
}