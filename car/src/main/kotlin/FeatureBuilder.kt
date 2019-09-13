class FeatureBuilder(private val carBuilder: CarBuilder) {
    val sunroof: Boolean
        get() {
            carBuilder.features.add(("sunroof"))
            return true
        }

    val selfdrive: Boolean
        get() {
            carBuilder.features.add(("selfdrive"))
            return true
        }
}