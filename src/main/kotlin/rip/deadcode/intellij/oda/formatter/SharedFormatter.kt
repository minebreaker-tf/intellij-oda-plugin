package rip.deadcode.intellij.oda.formatter

import rip.deadcode.intellij.oda.model.VariantForm

object SharedFormatter {

    fun format(variantForm: VariantForm): String {
        val regions = if (variantForm.regions != null) {
            "[" + variantForm.regions.joinToString(", ") + "] "
        } else ""
        return """${regions}${variantForm.text}"""
    }
}