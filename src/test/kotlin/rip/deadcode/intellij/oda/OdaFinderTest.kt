package rip.deadcode.intellij.oda

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test
import rip.deadcode.intellij.oda.OdaFinder.toWordId

internal class OdaFinderTest {

    @Test
    fun testToWordId() {

        var param = "word"
        var result = toWordId(param)
        assertThat(result).isEqualTo("word")

        param = "historical cost accounting"
        result = toWordId(param)
        assertThat(result).isEqualTo("historical_cost_accounting")

        param = "CAPITALIZED"
        result = toWordId(param)
        assertThat(result).isEqualTo("capitalized")
    }
}
