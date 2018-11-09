package rip.deadcode.intellij.oda.formatter

import com.google.common.truth.Truth.assertThat
import com.google.gson.Gson
import org.junit.jupiter.api.Test
import rip.deadcode.intellij.oda.formatter.ThesaurusFormatter.format
import rip.deadcode.intellij.oda.model.SynonymAntonym
import rip.deadcode.intellij.oda.model.ThesaurusEntry
import rip.deadcode.intellij.oda.model.ThesaurusLexicalEntry
import rip.deadcode.intellij.oda.model.ThesaurusSense

internal class ThesaurusFormatterTest {

    @Test
    fun testFormatThesaurusLexicalEntry() {
        val param = Gson().fromJson("""{
            "entries": [{
                "senses": [{ "synonyms": [{ "text": "Text" }] }]
            }],
            "lexicalCategory": "Category"
        }""", ThesaurusLexicalEntry::class.java)
        val result = format(param)
        assertThat(result).isEqualTo("<div><span>[Category]</span><div><p><span>Text</span></p></div></div>")
    }

    @Test
    fun testFormatThesaurusEntry() {
        val param = Gson().fromJson("""{
            "senses": [{ "synonyms": [{ "text": "Text" }] }],
            "variantForms": [{ "text": "Var1", "regions": [ "Reg1" ] }, { "text": "Var2", "regions": [ "Reg2" ] }]
        }""", ThesaurusEntry::class.java)
        val result = format(param)
        assertThat(result).isEqualTo("<div><p><span>Text</span></p><p>Variants: [Reg1] Var1, [Reg2] Var2</p></div>")
    }

    @Test
    fun testFormatThesaurusSense() {
        val param = Gson().fromJson("""{
            "synonyms": [
                { "text": "Text1" },
                { "text": "Text2" }
            ],
            "subsenses": [
                { "synonyms": [{ "text": "Sub1" }] }
            ]
        }""".trimIndent(), ThesaurusSense::class.java)
        val result = format(param)
        assertThat(result).isEqualTo("<p><span>Text1</span></p><p><span>Text2</span></p>" +
                "<div><h4>Subsenses</h4><p><span>Sub1</span></p></div>")
    }

    @Test
    fun testFormatSynonymAntonym() {
        val param = Gson().fromJson("""{
            "domains": ["Domain1", "Domain2"],
            "regions": ["Region1", "Region2"],
            "text": "FooBar"
        }""".trimIndent(), SynonymAntonym::class.java)
        val result = format(param)
        assertThat(result).isEqualTo("""<p><span>[Region1,Region2]</span><span>FooBar</span><span>(Domain1,Domain2)</span></p>""")
    }
}
