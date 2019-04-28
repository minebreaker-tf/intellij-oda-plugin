package rip.deadcode.intellij.oda.model

data class VariantForm(
        val domains: List<Domain>,
        val notes: List<CategorizedText>?,
        val pronunciations: List<Pronunciation>?,
        val regions: List<Region>?,
        val registers: List<Register>?,
        val text: String
)

data class Domain(
        val id: String,
        val text: String
)

data class Region(
        val id: String,
        val text: String
)

data class Register(
        val id: String,
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
        val domains: List<Domain>?,
        val notes: List<CategorizedText>?,
        val regions: List<Region>?,
        val registers: List<Register>?,
        val senseIds: List<String>?,
        val text: String
)
