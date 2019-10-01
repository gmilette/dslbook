package builder

import assertk.all
import assertk.assertThat
import assertk.assertions.contains
import assertk.assertions.hasSize
import assertk.assertions.isEqualTo
import model.Dish
import model.Menu
import org.junit.jupiter.api.Test

class NonDslTest {
    @Test
    fun `create menu`() {
        val dishes = mutableListOf<Dish>()

        val ingredientsForBlt = listOf("bacon", "lettuce", "tomato")
        val blt = Dish("blt", ingredientsForBlt)
        dishes.add(blt)

        val ingredientsForPizza = listOf("pepperoni", "mushrooms")
        val pizza = Dish("pizza", ingredientsForPizza)
        dishes.add(pizza)

        val menu = Menu("Sunrise Restaurant", dishes)

        assertThat(menu.name).isEqualTo("Sunrise Restaurant")
        assertThat(menu.dishes.size).isEqualTo(2)
        assertThat(menu.dishes[0].ingredients).all {
            hasSize(3)
            contains("bacon")
            contains("lettuce")
            contains("tomato")
        }
        assertThat(menu.dishes[1].ingredients.size).isEqualTo(2)
        assertThat(menu.dishes[1].ingredients).all {
            hasSize(2)
            contains("pepperoni")
            contains("mushrooms")
        }
    }
}