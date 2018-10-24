package rip.deadcode.intellij.oda.formatter

import com.google.common.truth.Truth.assertThat
import com.google.gson.Gson
import org.junit.jupiter.api.Test
import rip.deadcode.intellij.oda.model.Entry
import rip.deadcode.intellij.oda.model.LexicalEntry
import rip.deadcode.intellij.oda.model.RetrieveEntry
import rip.deadcode.intellij.oda.model.Sense

object EntryFormatterTest {

    @Test
    fun testFormat() {
        val param = Gson().fromJson(json, RetrieveEntry::class.java)
        val result = EntryFormatter.format(param)
        assertThat(result).isEqualTo(jsonResult)
    }

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

    private val json = """{
    "metadata": {
        "provider": "Oxford University Press"
    },
    "results": [
        {
            "id": "ace",
            "language": "en",
            "lexicalEntries": [
                {
                    "entries": [
                        {
                            "etymologies": [
                                "Middle English (denoting the ‘one’ on dice): via Old French from Latin as ‘unity, a unit’"
                            ],
                            "grammaticalFeatures": [
                                {
                                    "text": "Singular",
                                    "type": "Number"
                                }
                            ],
                            "homographNumber": "100",
                            "senses": [
                                {
                                    "definitions": [
                                        "a playing card with a single spot on it, ranked as the highest card in its suit in most card games"
                                    ],
                                    "domains": [
                                        "Cards"
                                    ],
                                    "examples": [
                                        {
                                            "registers": [
                                                "figurative"
                                            ],
                                            "text": "life had started dealing him aces again"
                                        },
                                        {
                                            "text": "the ace of diamonds"
                                        }
                                    ],
                                    "id": "m_en_gbus0005680.006",
                                    "short_definitions": [
                                        "playing card with single spot on it, ranked as highest card in its suit in most card games"
                                    ]
                                },
                                {
                                    "definitions": [
                                        "a person who excels at a particular sport or other activity"
                                    ],
                                    "domains": [
                                        "Sport"
                                    ],
                                    "examples": [
                                        {
                                            "text": "a motorcycle ace"
                                        }
                                    ],
                                    "id": "m_en_gbus0005680.010",
                                    "registers": [
                                        "informal"
                                    ],
                                    "short_definitions": [
                                        "person who excels at particular sport or other activity"
                                    ],
                                    "subsenses": [
                                        {
                                            "definitions": [
                                                "a pilot who has shot down many enemy aircraft"
                                            ],
                                            "domains": [
                                                "Air Force"
                                            ],
                                            "examples": [
                                                {
                                                    "text": "a Battle of Britain ace"
                                                }
                                            ],
                                            "id": "m_en_gbus0005680.011",
                                            "short_definitions": [
                                                "pilot who has shot down many enemy aircraft"
                                            ]
                                        }
                                    ],
                                    "thesaurusLinks": [
                                        {
                                            "entry_id": "ace",
                                            "sense_id": "t_en_gb0000173.001"
                                        }
                                    ]
                                },
                                {
                                    "definitions": [
                                        "(in tennis and similar games) a service that an opponent is unable to return and thus wins a point"
                                    ],
                                    "domains": [
                                        "Tennis"
                                    ],
                                    "examples": [
                                        {
                                            "text": "Nadal banged down eight aces in the set"
                                        }
                                    ],
                                    "id": "m_en_gbus0005680.013",
                                    "short_definitions": [
                                        "(in tennis and similar games) service that opponent is unable to return and thus wins point"
                                    ],
                                    "subsenses": [
                                        {
                                            "definitions": [
                                                "a hole in one"
                                            ],
                                            "domains": [
                                                "Golf"
                                            ],
                                            "examples": [
                                                {
                                                    "text": "his hole in one at the 15th was Senior's second ace as a professional"
                                                }
                                            ],
                                            "id": "m_en_gbus0005680.014",
                                            "registers": [
                                                "informal"
                                            ],
                                            "short_definitions": [
                                                "hole in one"
                                            ]
                                        }
                                    ]
                                }
                            ]
                        },
                        {
                            "etymologies": [
                                "early 21st century: abbreviation of asexual, with alteration of spelling on the model of ace"
                            ],
                            "grammaticalFeatures": [
                                {
                                    "text": "Singular",
                                    "type": "Number"
                                }
                            ],
                            "homographNumber": "200",
                            "senses": [
                                {
                                    "definitions": [
                                        "a person who has no sexual feelings or desires"
                                    ],
                                    "domains": [
                                        "Sex"
                                    ],
                                    "examples": [
                                        {
                                            "text": "both asexual, they have managed to connect with other aces offline"
                                        }
                                    ],
                                    "id": "m_en_gbus1190638.004",
                                    "short_definitions": [
                                        "asexual person"
                                    ]
                                }
                            ]
                        }
                    ],
                    "language": "en",
                    "lexicalCategory": "Noun",
                    "pronunciations": [
                        {
                            "audioFile": "http://audio.oxforddictionaries.com/en/mp3/ace_1_gb_1_abbr.mp3",
                            "dialects": [
                                "British English"
                            ],
                            "phoneticNotation": "IPA",
                            "phoneticSpelling": "eɪs"
                        }
                    ],
                    "text": "ace"
                },
                {
                    "entries": [
                        {
                            "grammaticalFeatures": [
                                {
                                    "text": "Positive",
                                    "type": "Degree"
                                }
                            ],
                            "homographNumber": "101",
                            "senses": [
                                {
                                    "definitions": [
                                        "very good"
                                    ],
                                    "examples": [
                                        {
                                            "text": "Ace! You've done it!"
                                        },
                                        {
                                            "text": "an ace swimmer"
                                        }
                                    ],
                                    "id": "m_en_gbus0005680.016",
                                    "registers": [
                                        "informal"
                                    ],
                                    "short_definitions": [
                                        "very good"
                                    ],
                                    "thesaurusLinks": [
                                        {
                                            "entry_id": "ace",
                                            "sense_id": "t_en_gb0000173.002"
                                        }
                                    ]
                                }
                            ]
                        },
                        {
                            "grammaticalFeatures": [
                                {
                                    "text": "Positive",
                                    "type": "Degree"
                                }
                            ],
                            "homographNumber": "201",
                            "senses": [
                                {
                                    "definitions": [
                                        "(of a person) having no sexual feelings or desires; asexual"
                                    ],
                                    "domains": [
                                        "Sex"
                                    ],
                                    "examples": [
                                        {
                                            "text": "I didn't realize that I was ace for a long time"
                                        }
                                    ],
                                    "id": "m_en_gbus1190638.006",
                                    "short_definitions": [
                                        "asexual"
                                    ]
                                }
                            ]
                        }
                    ],
                    "language": "en",
                    "lexicalCategory": "Adjective",
                    "pronunciations": [
                        {
                            "audioFile": "http://audio.oxforddictionaries.com/en/mp3/ace_1_gb_1_abbr.mp3",
                            "dialects": [
                                "British English"
                            ],
                            "phoneticNotation": "IPA",
                            "phoneticSpelling": "eɪs"
                        }
                    ],
                    "text": "ace"
                },
                {
                    "entries": [
                        {
                            "grammaticalFeatures": [
                                {
                                    "text": "Transitive",
                                    "type": "Subcategorization"
                                },
                                {
                                    "text": "Present",
                                    "type": "Tense"
                                }
                            ],
                            "homographNumber": "102",
                            "senses": [
                                {
                                    "definitions": [
                                        "(in tennis and similar games) serve an ace against (an opponent)"
                                    ],
                                    "domains": [
                                        "Tennis"
                                    ],
                                    "examples": [
                                        {
                                            "text": "he can ace opponents with serves of no more than 62 mph"
                                        }
                                    ],
                                    "id": "m_en_gbus0005680.020",
                                    "registers": [
                                        "informal"
                                    ],
                                    "short_definitions": [
                                        "(in tennis and similar games) serve ace against"
                                    ],
                                    "subsenses": [
                                        {
                                            "definitions": [
                                                "score an ace on (a hole) or with (a shot)"
                                            ],
                                            "domains": [
                                                "Golf"
                                            ],
                                            "examples": [
                                                {
                                                    "text": "there was a prize for the first player to ace the hole"
                                                }
                                            ],
                                            "id": "m_en_gbus0005680.026",
                                            "short_definitions": [
                                                "score ace on hole or with"
                                            ]
                                        }
                                    ]
                                },
                                {
                                    "definitions": [
                                        "achieve high marks in (a test or exam)"
                                    ],
                                    "examples": [
                                        {
                                            "text": "I aced my grammar test"
                                        }
                                    ],
                                    "id": "m_en_gbus0005680.028",
                                    "regions": [
                                        "North American"
                                    ],
                                    "registers": [
                                        "informal"
                                    ],
                                    "short_definitions": [
                                        "achieve high marks in"
                                    ],
                                    "subsenses": [
                                        {
                                            "definitions": [
                                                "outdo someone in a competitive situation"
                                            ],
                                            "examples": [
                                                {
                                                    "text": "the magazine won an award, acing out its rivals"
                                                }
                                            ],
                                            "id": "m_en_gbus0005680.029",
                                            "notes": [
                                                {
                                                    "text": "\"ace someone out\"",
                                                    "type": "wordFormNote"
                                                }
                                            ],
                                            "short_definitions": [
                                                "outdo someone in competitive situation"
                                            ]
                                        }
                                    ]
                                }
                            ]
                        }
                    ],
                    "language": "en",
                    "lexicalCategory": "Verb",
                    "pronunciations": [
                        {
                            "audioFile": "http://audio.oxforddictionaries.com/en/mp3/ace_1_gb_1_abbr.mp3",
                            "dialects": [
                                "British English"
                            ],
                            "phoneticNotation": "IPA",
                            "phoneticSpelling": "eɪs"
                        }
                    ],
                    "text": "ace"
                }
            ],
            "type": "headword",
            "word": "ace"
        }
    ]
}"""

    private val jsonResult = "<div><div><p>[Noun]</p><div><p><span>a playing card with a single spot on it, ranked as the highest card in its suit in most card games</span><span>(Cards)</span></p><p><span>a person who excels at a particular sport or other activity</span><span>(Sport)</span></p><div><h4>Subsenses</h4><p><span>a pilot who has shot down many enemy aircraft</span><span>(Air Force)</span></p></div><p><span>(in tennis and similar games) a service that an opponent is unable to return and thus wins a point</span><span>(Tennis)</span></p><div><h4>Subsenses</h4><p><span>a hole in one</span><span>(Golf)</span></p></div></div><div><p><span>a person who has no sexual feelings or desires</span><span>(Sex)</span></p></div></div><div><p>[Adjective]</p><div><p><span>very good</span></p></div><div><p><span>(of a person) having no sexual feelings or desires; asexual</span><span>(Sex)</span></p></div></div><div><p>[Verb]</p><div><p><span>(in tennis and similar games) serve an ace against (an opponent)</span><span>(Tennis)</span></p><div><h4>Subsenses</h4><p><span>score an ace on (a hole) or with (a shot)</span><span>(Golf)</span></p></div><p><span>[North American]</span><span>achieve high marks in (a test or exam)</span></p><div><h4>Subsenses</h4><p><span>outdo someone in a competitive situation</span></p></div></div></div></div>"
}
