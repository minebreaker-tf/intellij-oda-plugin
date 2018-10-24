package rip.deadcode.intellij.oda.model

import com.google.api.client.json.GenericJson
import com.google.gson.annotations.SerializedName

data class RetrieveEntry(
        val metadata: GenericJson?,
        val results: List<HeadwordEntry>?
)

data class HeadwordEntry(
        val id: String,
        val language: String,
        val lexicalEntries: List<LexicalEntry>,
        val pronunciationsList: List<Pronunciation>?,
        val type: String?,
        val word: String
)

data class LexicalEntry(
        val derivativeOf: List<RelatedEntry>?,
        val derivatives: List<RelatedEntry>?,
        val entries: List<Entry>?,
        val grammaticalFeatures: GrammaticalFeature?,
        val language: String,
        val lexicalCategory: String,
        val notes: List<CategorizedText>?,
        val pronunciations: List<Pronunciation>?,
        val text: String,
        val variantForms: List<VariantForm>?
)

data class Pronunciation(
        val audioFile: String?,
        val dialects: List<String>?,
        val phoneticNotation: String?,
        val phoneticSpelling: String?,
        val regions: List<String>?
)

data class RelatedEntry(
        val domains: List<String>?,
        val id: String,
        val language: String?,
        val regions: List<String>?,
        val text: String
)

data class Entry(
        val etymologies: List<String>?,
        val grammaticalFeatures: List<GrammaticalFeature>?,
        val homographNumber: String?,
        val notes: List<CategorizedText>?,
        val pronunciations: List<Pronunciation>?,
        val senses: List<Sense>?,
        val variantForms: List<VariantForm>?
)

data class Sense(
        val crossReferenceMarkers: List<String>?,
        val crossReference: List<CrossReference>?,
        val definitions: List<String>?,
        val domains: List<String>?,
        val examples: List<Example>?,
        val id: String?,
        val notes: List<CategorizedText>?,
        val pronunciations: List<Pronunciation>?,
        val regions: List<String>?,
        @SerializedName("short_definitions")
        val shortDefinitions: List<String>?,
        val subsenses: List<Sense>?,
        val thesaurusLinks: List<ThesaurusLink>?,
        val translations: List<Translation>?,
        val variantForms: List<VariantForm>?
)

data class CrossReference(
        val id: String,
        val text: String,
        val type: String
)

data class ThesaurusLink(
        @SerializedName("entry_id")
        val entryId: String,
        @SerializedName("sense_id")
        val senseId: String
)
