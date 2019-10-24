package builder

import model.Menu

class MenuBuilder(private val name:String) {
    var currentDish: String? = null

    // symbol table
    private val dishes = mutableMapOf<String, DishBuilder>()

    // builder style
    fun add(dishName: String, ingredient: String): MenuBuilder {
        getOrCreate(dishName).add(ingredient)
        return this
    }

    private fun getOrCreate(dishName: String): DishBuilder {
        var dish = dishes[dishName]
        if (dish == null) {
            dish = DishBuilder()
            dish.name(dishName)
            dishes[dishName] = dish
        }
        return dish
    }

    // uses the add context
    fun add(ingredient: String): MenuBuilder {
        currentDish?.let {
            getOrCreate(it).add(ingredient)
        } ?:
            throw Exception("no current Dish")
        return this
    }

    // set the context variable for which dish we are making
    fun dish(dishName: String): MenuBuilder {
        currentDish = dishName
        return this
    }

    // nested builders
    fun asPizza(): PizzaBuilder = PizzaBuilder(this)

    fun asSandwich(): SandwichBuilder = SandwichBuilder(this)

    fun build(): Menu {
        val dishes = dishes.map { it.value.build() }
        return Menu(name, dishes)
    }
}

