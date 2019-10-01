import CarColor.blue
import assertk.all
import assertk.assertThat
import assertk.assertions.contains
import assertk.assertions.hasSize
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CarBuilderTest {
    @Test
    fun `test car builder`() {
        val carBuilder = CarBuilder()
        val car = carBuilder {
            make = "Toyota"
            color = blue
            features {
                sunroof
                selfdrive
            }
        }

        assertEquals("Toyota", car.make)
        assertEquals(blue, car.color)
        assertThat(car.features).all {
            hasSize(2)
            contains("sunroof")
            contains("selfdrive")
        }
    }
}