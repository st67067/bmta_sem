class Obstacle(var x: Float, var y: Float, val width: Float = 100f, val height: Float = 200f) {
    fun move(speed: Float) {
        x -= speed // Překážka se pohybuje doleva
    }

    fun checkCollision(playerX: Float, playerY: Float, playerRadius: Float): Boolean {
        val closestX = playerX.coerceIn(x, x + width)
        val closestY = playerY.coerceIn(y, y + height)
        val distanceX = playerX - closestX
        val distanceY = playerY - closestY
        return (distanceX * distanceX + distanceY * distanceY) < (playerRadius * playerRadius)
    }
}