package infixmath

import org.junit.jupiter.api.Test

class InfixMathTest {
    @Test
    fun `test infix has`() {
        val builder = Builder()

        builder.create {
            2 combineWith 1
        }
    }
}