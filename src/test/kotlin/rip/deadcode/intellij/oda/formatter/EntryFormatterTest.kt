package rip.deadcode.intellij.oda.formatter

import com.google.common.truth.Truth.assertThat
import com.google.gson.Gson
import org.junit.jupiter.api.Test
import rip.deadcode.intellij.oda.model.Entry
import rip.deadcode.intellij.oda.model.LexicalEntry
import rip.deadcode.intellij.oda.model.RetrieveEntry
import rip.deadcode.intellij.oda.model.Sense

class EntryFormatterTest {

    @Test
    fun testFormatRetrieveEntry() {
        val param = Gson().fromJson("""{
            "results": [
                {
                    "lexicalEntries": [
                        {
                            "lexicalCategory": "Noun",
                            "entries": [
                                { "senses": [ { "definitions": ["Definition"] } ] }
                            ]
                        }
                    ]
                }
            ]
        }""".trimIndent(), RetrieveEntry::class.java)
        val result = EntryFormatter.format(param)
        assertThat(result).isEqualTo("""<div><div><p>[Noun]</p><div><p><span>Definition</span></p></div></div></div>""")
    }

    @Test
    fun testFormatLexicalEntry() {
        val param = Gson().fromJson("""{
            "lexicalCategory": "Noun",
            "entries": [
                { "senses": [ { "definitions": ["Definition"] } ] }
            ]
        }""".trimIndent(), LexicalEntry::class.java)
        val result = EntryFormatter.format(param)
        assertThat(result).isEqualTo("""<div><p>[Noun]</p><div><p><span>Definition</span></p></div></div>""")
    }


    @Test
    fun testFormatEntry() {
        val param = Gson().fromJson("""{
            senses: [
                { "definitions": ["Definition1"] },
                { "definitions": ["Definition2"] }
            ]
        }""".trimIndent(), Entry::class.java)
        val result = EntryFormatter.format(param)
        assertThat(result).isEqualTo("""<div><p><span>Definition1</span></p><p><span>Definition2</span></p></div>""")
    }

    @Test
    fun testFormatSense() {
        val param = Gson().fromJson("""{
            "domains": ["Domain1", "Domain2"],
            "regions": ["Region1", "Region2"],
            "definitions": ["Definition1", "Definition2"]
        }""".trimIndent(), Sense::class.java)
        val result = EntryFormatter.format(param)
        assertThat(result).isEqualTo("""<p><span>[Region1,Region2]</span><span>Definition1,Definition2</span><span>(Domain1,Domain2)</span></p>""")
    }
}
