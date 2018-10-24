package rip.deadcode.intellij.oda.model

data class VariantForm(
        val regions: List<String>?,
        val text: String
)

data class GrammaticalFeature(
        val text: String,
        val type: String
)

data class CategorizedText(
        val id: String?,
        val text: String,
        val type: String
)

data class Example(
        val definitions: List<String>?,
        val domains: List<String>?,
        val notes: List<CategorizedText>?,
        val regions: List<String>?,
        val registers: List<String>?,
        val senseIds: List<String>?,
        val text: String,
        val translations: List<Translation>?
)

data class Translation(
        val domains: List<String>?,
        val grammaticalFeatures: List<GrammaticalFeature>?,
        val language: String,
        val notes: List<CategorizedText>?,
        val regions: List<String>?,
        val registers: List<String>?,
        val text: String
)
