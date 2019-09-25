package infixmath

class Builder {
    infix fun Int.combineWith(other: Int): Int = this + other

    fun create(block: Builder.() -> Unit) {
        block()
    }
}
