import CarColor.blue
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
        assertEquals(2, car.features.size)
    }
}