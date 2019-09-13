package builder

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class BuilderTest {
    @Test
    fun `create restaurant`() {
        val builder = MenuBuilder("Sunrise Restaurant")

        val menu = builder.build()

        assertThat(menu.name).isEqualTo("Sunrise Restaurant")
    }
}