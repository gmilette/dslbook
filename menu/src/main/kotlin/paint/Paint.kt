package paint

fun main() {
    val paint = Paint()
    paint.start(10,10)
    paint.lineFromTo(10,10,10,20)
    paint.lineFromTo(10,20,20,20)
    paint.lineFromTo(20,20,10,20)
}

fun dslPaint() {
    val paint = Paint()
    paint.moveTo(10,10)
    paint.lineTo(10,20)
    paint.lineTo(20,20)
    paint.lineTo(10,20)
}

class Paint() {
    fun start(x: Int, y: Int) {

    }

    fun lineFromTo(fromX: Int, fromY: Int, toX: Int, toY: Int) {

    }

    fun moveTo(x: Int, y: Int) {

    }

    fun lineTo(x: Int, y: Int) {

    }

}