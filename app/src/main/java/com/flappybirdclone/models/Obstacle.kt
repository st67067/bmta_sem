class Obstacle(var x: float, var y: float, val width: float = 100f, val height: Float = 200f) {
    fun move(speed: Float) {
        x -= speed //Pohyb překážky směrem doleva
    }
}