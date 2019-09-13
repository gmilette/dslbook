class CarBuilder {
    var make: String = ""
    var features = mutableListOf<String>()
    var color: CarColor = CarColor.nopreference

    operator fun invoke(block: CarBuilder.() -> Unit): Car {
        block()
        return build()
    }

    fun features(block: FeatureBuilder.() -> Unit) {
        val featureBuilder = FeatureBuilder(this)
        featureBuilder.block()
    }

    fun build() : Car {
        return Car(make, color, features)
    }
}