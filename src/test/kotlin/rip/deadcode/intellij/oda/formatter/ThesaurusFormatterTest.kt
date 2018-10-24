package rip.deadcode.intellij.oda.formatter

import com.google.common.truth.Truth.assertThat
import com.google.gson.Gson
import org.junit.jupiter.api.Test
import rip.deadcode.intellij.oda.formatter.ThesaurusFormatter.format
import rip.deadcode.intellij.oda.model.*

internal class ThesaurusFormatterTest {

    @Test
    fun testFormat() {
        val param = Gson().fromJson(json, Thesaurus::class.java)
        val result = ThesaurusFormatter.format(param)
        assertThat(result).isEqualTo(jsonResult)
    }

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
              "homographNumber": "000",
              "senses": [
                {
                  "examples": [
                    {
                      "text": "a rowing ace"
                    }
                  ],
                  "id": "t_en_gb0000173.001",
                  "registers": [
                    "informal"
                  ],
                  "subsenses": [
                    {
                      "id": "id0d3b47dd-8fb3-4f10-9242-a7360d97fdc2",
                      "registers": [
                        "informal"
                      ],
                      "synonyms": [
                        {
                          "id": "wunderkind",
                          "language": "en",
                          "text": "wunderkind"
                        }
                      ]
                    },
                    {
                      "id": "idb877b692-457c-45e8-a92c-97644810957c",
                      "registers": [
                        "informal"
                      ],
                      "synonyms": [
                        {
                          "id": "demon",
                          "language": "en",
                          "text": "demon"
                        },
                        {
                          "id": "pro",
                          "language": "en",
                          "text": "pro"
                        },
                        {
                          "id": "wizard",
                          "language": "en",
                          "text": "wizard"
                        },
                        {
                          "id": "hotshot",
                          "language": "en",
                          "text": "hotshot"
                        },
                        {
                          "id": "whizz",
                          "language": "en",
                          "text": "whizz"
                        },
                        {
                          "id": "wiz",
                          "language": "en",
                          "text": "wiz"
                        },
                        {
                          "id": "ninja",
                          "language": "en",
                          "text": "ninja"
                        }
                      ]
                    },
                    {
                      "id": "idc0d83784-acc8-4883-a584-b8cb21788ba5",
                      "regions": [
                        "British"
                      ],
                      "registers": [
                        "informal"
                      ],
                      "synonyms": [
                        {
                          "id": "dab_hand",
                          "language": "en",
                          "text": "dab hand"
                        }
                      ]
                    },
                    {
                      "id": "id6707d21c-fe6e-48cc-a0a5-cd2609ec324b",
                      "regions": [
                        "North American"
                      ],
                      "registers": [
                        "informal"
                      ],
                      "synonyms": [
                        {
                          "id": "maven",
                          "language": "en",
                          "text": "maven"
                        },
                        {
                          "id": "crackerjack",
                          "language": "en",
                          "text": "crackerjack"
                        }
                      ]
                    }
                  ],
                  "synonyms": [
                    {
                      "id": "expert",
                      "language": "en",
                      "text": "expert"
                    },
                    {
                      "id": "master",
                      "language": "en",
                      "text": "master"
                    },
                    {
                      "id": "genius",
                      "language": "en",
                      "text": "genius"
                    },
                    {
                      "id": "virtuoso",
                      "language": "en",
                      "text": "virtuoso"
                    },
                    {
                      "id": "maestro",
                      "language": "en",
                      "text": "maestro"
                    },
                    {
                      "id": "professional",
                      "language": "en",
                      "text": "professional"
                    },
                    {
                      "id": "adept",
                      "language": "en",
                      "text": "adept"
                    },
                    {
                      "id": "past_master",
                      "language": "en",
                      "text": "past master"
                    },
                    {
                      "id": "doyen",
                      "language": "en",
                      "text": "doyen"
                    },
                    {
                      "id": "champion",
                      "language": "en",
                      "text": "champion"
                    },
                    {
                      "id": "star",
                      "language": "en",
                      "text": "star"
                    },
                    {
                      "id": "winner",
                      "language": "en",
                      "text": "winner"
                    }
                  ]
                }
              ]
            }
          ],
          "language": "en",
          "lexicalCategory": "Noun",
          "text": "ace"
        },
        {
          "entries": [
            {
              "homographNumber": "001",
              "senses": [
                {
                  "examples": [
                    {
                      "text": "an ace tennis player"
                    }
                  ],
                  "id": "t_en_gb0000173.002",
                  "registers": [
                    "informal"
                  ],
                  "subsenses": [
                    {
                      "id": "id3f7aa369-1511-4539-a00f-916bed488e4d",
                      "registers": [
                        "informal"
                      ],
                      "synonyms": [
                        {
                          "id": "great",
                          "language": "en",
                          "text": "great"
                        },
                        {
                          "id": "terrific",
                          "language": "en",
                          "text": "terrific"
                        },
                        {
                          "id": "tremendous",
                          "language": "en",
                          "text": "tremendous"
                        },
                        {
                          "id": "superb",
                          "language": "en",
                          "text": "superb"
                        },
                        {
                          "id": "smashing",
                          "language": "en",
                          "text": "smashing"
                        },
                        {
                          "id": "fantastic",
                          "language": "en",
                          "text": "fantastic"
                        },
                        {
                          "id": "stellar",
                          "language": "en",
                          "text": "stellar"
                        },
                        {
                          "id": "sensational",
                          "language": "en",
                          "text": "sensational"
                        },
                        {
                          "id": "fabulous",
                          "language": "en",
                          "text": "fabulous"
                        },
                        {
                          "id": "fab",
                          "language": "en",
                          "text": "fab"
                        },
                        {
                          "id": "crack",
                          "language": "en",
                          "text": "crack"
                        },
                        {
                          "id": "hotshot",
                          "language": "en",
                          "text": "hotshot"
                        },
                        {
                          "id": "a1",
                          "language": "en",
                          "text": "A1"
                        },
                        {
                          "id": "mean",
                          "language": "en",
                          "text": "mean"
                        },
                        {
                          "id": "demon",
                          "language": "en",
                          "text": "demon"
                        },
                        {
                          "id": "awesome",
                          "language": "en",
                          "text": "awesome"
                        },
                        {
                          "id": "magic",
                          "language": "en",
                          "text": "magic"
                        },
                        {
                          "id": "wicked",
                          "language": "en",
                          "text": "wicked"
                        },
                        {
                          "id": "tip-top",
                          "language": "en",
                          "text": "tip-top"
                        },
                        {
                          "id": "top-notch",
                          "language": "en",
                          "text": "top-notch"
                        }
                      ]
                    },
                    {
                      "id": "id0e0e99ad-a273-40b5-b4a8-c24446d29164",
                      "regions": [
                        "British"
                      ],
                      "registers": [
                        "informal"
                      ],
                      "synonyms": [
                        {
                          "id": "brilliant",
                          "language": "en",
                          "text": "brilliant"
                        },
                        {
                          "id": "brill",
                          "language": "en",
                          "text": "brill"
                        }
                      ]
                    },
                    {
                      "id": "id0dc37dbe-c989-4bae-9343-4cbcac35d9bf",
                      "regions": [
                        "North American"
                      ],
                      "registers": [
                        "informal"
                      ],
                      "synonyms": [
                        {
                          "id": "badass",
                          "language": "en",
                          "text": "badass"
                        }
                      ]
                    },
                    {
                      "id": "idb5c892a7-1a3a-4f9a-88df-7813e62f86e4",
                      "registers": [
                        "informal",
                        "vulgar slang"
                      ],
                      "synonyms": [
                        {
                          "id": "shit-hot",
                          "language": "en",
                          "text": "shit-hot"
                        }
                      ]
                    }
                  ],
                  "synonyms": [
                    {
                      "id": "excellent",
                      "language": "en",
                      "text": "excellent"
                    },
                    {
                      "id": "very_good",
                      "language": "en",
                      "text": "very good"
                    },
                    {
                      "id": "first-rate",
                      "language": "en",
                      "text": "first-rate"
                    },
                    {
                      "id": "first-class",
                      "language": "en",
                      "text": "first-class"
                    },
                    {
                      "id": "marvellous",
                      "language": "en",
                      "text": "marvellous"
                    },
                    {
                      "id": "wonderful",
                      "language": "en",
                      "text": "wonderful"
                    },
                    {
                      "id": "magnificent",
                      "language": "en",
                      "text": "magnificent"
                    },
                    {
                      "id": "outstanding",
                      "language": "en",
                      "text": "outstanding"
                    },
                    {
                      "id": "superlative",
                      "language": "en",
                      "text": "superlative"
                    },
                    {
                      "id": "formidable",
                      "language": "en",
                      "text": "formidable"
                    },
                    {
                      "id": "virtuoso",
                      "language": "en",
                      "text": "virtuoso"
                    },
                    {
                      "id": "masterly",
                      "language": "en",
                      "text": "masterly"
                    },
                    {
                      "id": "expert",
                      "language": "en",
                      "text": "expert"
                    },
                    {
                      "id": "champion",
                      "language": "en",
                      "text": "champion"
                    },
                    {
                      "id": "fine",
                      "language": "en",
                      "text": "fine"
                    },
                    {
                      "id": "consummate",
                      "language": "en",
                      "text": "consummate"
                    },
                    {
                      "id": "skilful",
                      "language": "en",
                      "text": "skilful"
                    },
                    {
                      "id": "adept",
                      "language": "en",
                      "text": "adept"
                    }
                  ]
                }
              ]
            }
          ],
          "language": "en",
          "lexicalCategory": "Adjective",
          "text": "ace"
        }
      ],
      "type": "headword",
      "word": "ace"
    }
  ]
}"""

   private  val jsonResult = "<div><div><span>[Noun]</span><div><p><span>expert</span></p><p><span>master</span></p><p><span>genius</span></p><p><span>virtuoso</span></p><p><span>maestro</span></p><p><span>professional</span></p><p><span>adept</span></p><p><span>past master</span></p><p><span>doyen</span></p><p><span>champion</span></p><p><span>star</span></p><p><span>winner</span></p><div><h4>Subsenses</h4><p><span>wunderkind</span></p><p><span>demon</span></p><p><span>pro</span></p><p><span>wizard</span></p><p><span>hotshot</span></p><p><span>whizz</span></p><p><span>wiz</span></p><p><span>ninja</span></p><p><span>dab hand</span></p><p><span>maven</span></p><p><span>crackerjack</span></p></div></div></div><div><span>[Adjective]</span><div><p><span>excellent</span></p><p><span>very good</span></p><p><span>first-rate</span></p><p><span>first-class</span></p><p><span>marvellous</span></p><p><span>wonderful</span></p><p><span>magnificent</span></p><p><span>outstanding</span></p><p><span>superlative</span></p><p><span>formidable</span></p><p><span>virtuoso</span></p><p><span>masterly</span></p><p><span>expert</span></p><p><span>champion</span></p><p><span>fine</span></p><p><span>consummate</span></p><p><span>skilful</span></p><p><span>adept</span></p><div><h4>Subsenses</h4><p><span>great</span></p><p><span>terrific</span></p><p><span>tremendous</span></p><p><span>superb</span></p><p><span>smashing</span></p><p><span>fantastic</span></p><p><span>stellar</span></p><p><span>sensational</span></p><p><span>fabulous</span></p><p><span>fab</span></p><p><span>crack</span></p><p><span>hotshot</span></p><p><span>A1</span></p><p><span>mean</span></p><p><span>demon</span></p><p><span>awesome</span></p><p><span>magic</span></p><p><span>wicked</span></p><p><span>tip-top</span></p><p><span>top-notch</span></p><p><span>brilliant</span></p><p><span>brill</span></p><p><span>badass</span></p><p><span>shit-hot</span></p></div></div></div></div>"
}
