package structure

import model.Dish
import model.Menu

@DslMarker
annotation class MenuBuilderDsl

@MenuBuilderDsl
class MenuBuilder(val name: String) {
    val dishBuilders = mutableMapOf<String, DishBuilder>()

    operator fun invoke(block: MenuBuilder.() -> Unit) {
        block()
    }

    fun dish(name: String, block: DishBuilder.() -> Unit): DishBuilder {
        val builder = DishBuilder(name)
        dishBuilders[name] = builder
        builder.block()
        return builder
    }

    fun String.has(block: DishBuilder.() -> Unit) {
        dish(this, block)
    }

    fun create(block: MenuBuilder.() -> Unit): Menu {
        block()
        return build()
    }

    fun build(): Menu {
        val dishes = dishBuilders.map { it.value.build() }
        return Menu(name, dishes)
    }
}