package builder

import assertk.all
import assertk.assertThat
import assertk.assertions.contains
import assertk.assertions.hasSize
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test
import builder.PizzaBuilder.Topping.mushrooms
import builder.PizzaBuilder.Topping.pepperoni
import builder.SandwichBuilder.Topping.ham
import builder.SandwichBuilder.Condiment.mayonnaise

class FunctionChainTest {
    @Test
    fun `create menu with function chain`() {
        val builder = MenuBuilder("Sunrise Restaurant")

        builder.add("blt", "bacon")
            .add("blt", "lettuce")
            .add("blt", "tomato")

        val menu = builder.build()

        assertThat(menu.dishes[0].name).isEqualTo("blt")
        assertThat(menu.dishes[0].ingredients).all {
            hasSize(3)
            contains("bacon")
            contains("lettuce")
            contains("tomato")
        }
    }

    @Test
    fun `create menu with pizza with function chain`() {
        val builder = MenuBuilder("Sunrise Restaurant")

        builder.add("pizza", "cheese")
            .add("pizza", "pepperoni")
            .add("pizza", "mushroom")

        val menu = builder.build()

        assertThat(menu.dishes[0].name).isEqualTo("pizza")
        assertThat(menu.dishes[0].ingredients).all {
            hasSize(3)
            contains("cheese")
            contains("pepperoni")
            contains("mushroom")
        }
    }

    @Test
    fun `create menu with context variable`() {
        val menu = MenuBuilder("Sunrise Restaurant")
            .dish("blt").add("bacon").add("lettuce").add("tomato")
            .dish("pizza").add("cheese").add("pepperoni").add("mushroom")
            .build()

        assertThat(menu.dishes[0].name).isEqualTo("blt")
        assertThat(menu.dishes[0].ingredients).all {
            hasSize(3)
            contains("bacon")
            contains("lettuce")
            contains("tomato")
        }
        assertThat(menu.dishes[1].ingredients).all {
            hasSize(3)
            contains("cheese")
            contains("pepperoni")
            contains("mushroom")
        }
    }

    @Test
    fun `create menu with nested builders`() {
        val builder = MenuBuilder("Sunrise Restaurant")

        builder.dish("ham and cheese").add("cheese").asSandwich().topping(ham).condiment(mayonnaise)
        builder.dish("pizza").add("cheese").asPizza().topping(pepperoni).topping(mushrooms)

        val menu = builder.build()

        assertThat(menu.dishes[0].name).isEqualTo("ham and cheese")
        assertThat(menu.dishes[0].ingredients).all {
            hasSize(3)
            contains("cheese")
            contains("ham")
            contains("mayonnaise")
        }
        assertThat(menu.dishes[1].name).isEqualTo("pizza")
        assertThat(menu.dishes[1].ingredients).all {
            hasSize(3)
            contains("cheese")
            contains("pepperoni")
            contains("mushrooms")
        }
    }

    @Test
    fun `lookup dishes using symbols`() {
        val builder = MenuBuilder("Sunrise Restaurant")

        val menu = builder
            .add("blt", "bacon")
            .add("blt", "lettuce")
            .add("blt", "tomato")
    }

    @Test
    fun `temp variables`() {
        val builder = MenuBuilder("Sunrise Restaurant")

        // currently not supported by the code
        // but shows what the DSL would have looked like
//        val blt = builder.add("blt")
//        blt.add("bacon")
//        val pizza = builder.add("pizza")
//        pizza.add("cheese")
    }
}