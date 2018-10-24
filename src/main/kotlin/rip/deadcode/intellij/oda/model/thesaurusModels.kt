package rip.deadcode.intellij.oda.model

import com.google.api.client.json.GenericJson

// https://developer.oxforddictionaries.com/documentation#!/Thesaurus/get_entries_source_lang_word_id_synonyms

data class Thesaurus(
        val metadata: GenericJson?,
        val results: List<HeadwordThesaurus>?
)

data class HeadwordThesaurus(
        val id: String,
        val language: String,
        val lexicalEntries: List<ThesaurusLexicalEntry>,
        val type: String?,
        val word: String
)

data class ThesaurusLexicalEntry(
        val entries: List<ThesaurusEntry>?,
        val language: String,
        val lexicalCategory: String,
        val text: String,
        val variantForms: List<VariantForm>?
)

data class ThesaurusEntry(
        val homographNumber: String?,
        val senses: List<ThesaurusSense>?,
        val variantForms: List<VariantForm>?
)

data class ThesaurusSense(
        val antonyms: List<SynonymAntonym>?,
        val domains: List<String>?,
        val examples: List<Example>?,
        val id: String?,
        val regions: List<String>?,
        val registers: List<String>?,
        val subsenses: List<ThesaurusSense>?,
        val synonyms: List<SynonymAntonym>?
)

data class SynonymAntonym(
        val domains: List<String>?,
        val id: String,
        val language: String?,
        val regions: List<String>?,
        val registers: List<String>?,
        val text: String
)
