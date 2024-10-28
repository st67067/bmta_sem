class Oin(var x: Float, var y: Float, val radius: Float = 20f) {
    fun move (speed: Float)  {
        x -= speed //pohyb mince
    }
}