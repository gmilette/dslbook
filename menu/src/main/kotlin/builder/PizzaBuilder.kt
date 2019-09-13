package builder

class PizzaBuilder(val menu: MenuBuilder) {
    enum class Topping {
        pepperoni,
        mushrooms,
        onions
    }

    fun topping(topping: Topping): PizzaBuilder {
        menu.add(topping.name)
        return this
    }
}