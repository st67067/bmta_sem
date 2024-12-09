class Coin(var x: Float, var y: Float, val radius: Float = 20f) {
    fun move (speed: Float)  {
        x -= speed //pohyb mince doleva
    }
    fun checkCollision(playerX: Float, playerY: Float, playerRadius: Float): Boolean {
        //Detekce kolize mezi hříčema mincí
        val distance = Math.sqrt((
                (x - playerX) *
                (x - playerX) +
                (y - playerY) *
                (y - playerY)).toDouble())
        return distance < radius + playerRadius
    }
}