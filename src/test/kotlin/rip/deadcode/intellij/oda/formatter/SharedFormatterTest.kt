package rip.deadcode.intellij.oda.formatter

import com.google.common.truth.Truth.assertThat
import com.google.gson.Gson
import org.junit.jupiter.api.Test
import rip.deadcode.intellij.oda.model.VariantForm

internal class SharedFormatterTest {

    @Test
    fun testFormatVariantForms() {
        val param = Gson().fromJson("""{
            "text": "Text",
            "regions": [ "Region1", "Region2" ]
        }""", VariantForm::class.java)
        val result = SharedFormatter.format(param)
        assertThat(result).isEqualTo("[Region1, Region2] Text")
    }
}
