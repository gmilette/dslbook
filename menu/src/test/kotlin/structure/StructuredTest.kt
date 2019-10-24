package structure

import assertk.all
import assertk.assertThat
import assertk.assertions.contains
import assertk.assertions.hasSize
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test
import structure.DishBuilder.Size.small

class StructuredTest {
    @Test
    fun `create two menu`() {
        val builder = MenuBuilder("Sunrise Restaurant")
        val menu = builder {
            dish("pizza") {
                +"cheese"
                +"pepperoni"
                +"mushroom"
            }
            dish("blt") {
                bacon
                lettuce
                tomato
            }
        }
    }

    @Test
    fun `create single section menu`() {
        val builder = MenuBuilder("Sunrise Restaurant")
        val menu = builder.create {
            // lambda
            dish("pizza") {
                add("cheese")
                add("pepperoni")
                add("mushroom")
                // assignment with enum and static import
                size = small
            }
            // using operator
            dish("veggie pizza") {
                +"mushroom"
                +"onion"
                +"peppers"
            }
            // using properties
            dish("blt") {
                bacon
                lettuce
                tomato
            }
            // extension function
            "tofu scramble".has {
                +"tofu"
                +"avocado"
                +"tomato"
            }
//            dish("tofu scramble") {
//                +"tofu"
//                +"avocado"
//                +"tomato"
//            }
        }
        assertThat(menu.dishes.size).isEqualTo(4)
        assertThat(menu.dishes[0].name).isEqualTo("pizza")
        assertThat(menu.dishes[1].name).isEqualTo("veggie pizza")
        assertThat(menu.dishes[2].name).isEqualTo("blt")
        assertThat(menu.dishes[3].name).isEqualTo("tofu scramble")
        assertThat(menu.dishes[1].ingredients).all {
            hasSize(3)
            contains("mushroom")
            contains("onion")
            contains("peppers")
        }
        assertThat(menu.dishes[2].ingredients).all {
            hasSize(3)
            contains("bacon")
            contains("lettuce")
            contains("tomato")
        }
        assertThat(menu.dishes[3].ingredients).all {
            hasSize(3)
            contains("tofu")
            contains("avocado")
            contains("tomato")
        }
    }

    @Test
    fun `create menu with pizza`() {
        val builder = MenuBuilder("Sunrise Restaurant")
        val menu = builder.create {
            // lambda
            dish("pizza") {
                add("cheese")
                add("pepperoni")
                add("mushroom")
            }
        }

        assertThat(menu.dishes[0].name).isEqualTo("pizza")
        assertThat(menu.dishes[0].ingredients).all {
            hasSize(3)
            contains("cheese")
            contains("pepperoni")
            contains("mushroom")
        }
    }

    @Test
    fun `test dsl marker`() {
        val builder = MenuBuilder("Sunrise Restaurant")
        val menu = builder.create {
            // lambda
            dish("pizza") {
                add("cheese")
                add("pepperoni")
                add("mushroom")
                // fails because of @DSLMarker
//                dish ("another pizza") {
//                    add("cheese")
//                }
            }
        }
        assertThat(menu.dishes[0].name).isEqualTo("pizza")
        assertThat(menu.dishes[0].ingredients).all {
            hasSize(3)
            contains("cheese")
            contains("pepperoni")
            contains("mushroom")
        }
    }
}