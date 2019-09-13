package infix

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test
import infix.MenuBuilder.ingredients
import infix.MenuBuilder.condiments
import infix.MenuBuilder.CondimentType.mayonnaise
import infix.MenuBuilder.CondimentType.mustard

class InfixTest {
    @Test
    fun `test infix has`() {
        val builder = MenuBuilder("Sunrise Restaurant")
        val menu = builder {
            "blt" has "bacon"
            "blt" has "lettuce"
            "blt" has "tomato"

            "pizza" has "cheese"
            "pizza" has "pepperoni"
        }
        assertThat(menu.dishes[0].name).isEqualTo("blt")
        assertThat(menu.dishes[0].ingredients.size).isEqualTo(3)
        assertThat(menu.dishes[1].name).isEqualTo("pizza")
        assertThat(menu.dishes[1].ingredients.size).isEqualTo(2)
    }
    @Test
    fun `test infix`() {
        val builder = MenuBuilder("Sunrise Restaurant")
        val menu = builder {
            "blt" has "bacon"
            "blt" has "lettuce"
            "blt" has "tomato"

            "pizza" has "cheese"
            "pizza" has "pepperoni"

            "blt" with ingredients named "bacon"
            "blt" with condiments named mayonnaise
            "blt" with condiments named mustard

            "blt" with ingredients named "bacon" and "lettuce" and "tomato"
            "blt" with condiments named mayonnaise and mustard

            "blt" with ingredients named "bacon" and "lettuce" and "tomato" and condiments named mayonnaise and mustard
        }
        assertThat(menu.dishes[0].name).isEqualTo("blt")
    }
}