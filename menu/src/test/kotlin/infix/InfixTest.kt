package infix

import assertk.all
import assertk.assertThat
import assertk.assertions.contains
import assertk.assertions.hasSize
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
        assertThat(menu.dishes[0].ingredients).all {
            contains("bacon")
            contains("lettuce")
            contains("tomato")
            hasSize(3)
        }
        assertThat(menu.dishes[1].name).isEqualTo("pizza")
        assertThat(menu.dishes[1].ingredients).all {
            hasSize(2)
            contains("cheese")
            contains("pepperoni")
        }
    }

    @Test
    fun `test infix create`() {
        val builder = MenuBuilder("Sunrise Restaurant")
        val menu = builder {
            "blt" with ingredients named "bacon" and "lettuce" and "tomato"
            "pizza" with ingredients named "cheese" and "pepperoni" and "mushroom"
        }
        assertThat(menu.dishes[0].name).isEqualTo("blt")
        assertThat(menu.dishes[0].ingredients).all {
            contains("bacon")
            contains("lettuce")
            contains("tomato")
            hasSize(3)
        }
        assertThat(menu.dishes[1].name).isEqualTo("pizza")
        assertThat(menu.dishes[1].ingredients).all {
            hasSize(3)
            contains("cheese")
            contains("pepperoni")
            contains("mushroom")
        }
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
        assertThat(menu.dishes[0].ingredients).all {
            contains("bacon")
            contains("mayonnaise")
            contains("mustard")
        }
    }
}