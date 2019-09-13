package infix

import model.Dish

class DishBuilder(val name: String) {
    val ingredients = mutableListOf<String>()

    fun add(ingredient: String): DishBuilder {
        ingredients.add(ingredient)
        return this
    }

    fun build(): Dish {
        return Dish(name, ingredients)
    }
}

