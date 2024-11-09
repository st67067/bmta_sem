/*class GameView(context: Context, attrs: AttributeSets) : View(context, attrs) {

    //Barvy pro vykreslování (pouze prozatím)
    private val playerPaint = Paint().apply { color = Color.BLUE }

    //vytvoř hráče
    private var player = Player(100f, height /2f)
    private val obstacles = mutableListOf<Obstacle>()
    private var sceneSpeed = 5f //rychlost jakou se scéna pohybuje doleva
    private var distance = 0 //počítadlo vzdálenosti


    //Aktualizace stavu hry - bude se volat v herní smyčce
    private val gameLoop = Runnable {
        update()
        invalidate() //Znovu překreslí obrazovku
        postDelayed(gameLoop, 16) //Smyčka obnovuje obrazovku zhruba každých 16ms (-60FPS)
    }

    init {
        startGame()
    }

        //Spuštění hry
        private fun startGame() {
            post(gameLoop) //spustí herní smyčku (asi)
        }
//aktualizace stavu hry
        private fun update() {
            player.update() // aktualizuj pozici hrace na základě gravitace a rychlosti
            updateObstacles() //posune překážky a přídá nové
    checkCollisions() //kontorla kolizí
    distance += 1 //Zvýšení vzdálensoti při každém cyklu
        }

    private fun updateObstacles() {
        for (obstacle in obstacles) {
            obstacle.move(sceneSpeed)
        }

        //Přidávání nových překážek
        if (obstacles.isEmpty() || obstacles.last().x < width - 300) {
        obstacles.add(Obstacle(width.toFloat(), randomYPosition()))
        }

        //odstran staré překážky mimo obrazovku
        obtacles.removeif { it.x + it.width < 0 }
    }

    privatew fun updateCoins(){
        for (coin in coins) {
            coin.move(sceneSpeed)
        }
        coins.removeIf{ it.x + it.radius < 0 }
    }

    private fun checkCollisions() {
        for (obstacle in obstacles) {
            if (RectF(player.x - 25, player.y - 25, player.x + 25, player.y + 25)
                    .intersect(
                        obstacle.x,
                        obstacle.y,
                        obstacle.x + obstacle.width,
                        obstacle.y + obstacle.height)) {
                player.loseLife()
            }
        }
        for (coin in coins) {
            if (rectF(player.x -25, player.y - 25, player.x + 25, player.y + 25)
                    .intersect(coin.x -coin.radius, coin.y - coin.radius, coin.x + coin.radius, coin.y + coin.radius)) {
                score += 1
                coins.remove(coin)
                break
            }
        }
    }


//metoda pro zpracování dotyku (skok)
        override fun onTouchEvent(event: MotionEvent?): Boolean {
            if (event?.action == MotionEvent.ACTION_DOWN) {
                player.jump() //Když hráč tapne tak se skáče (zatím)
            }
            return true
        }

        //Kreslení hry
        override fun onDraw(canvas: Canvas?) {
            super.onDraw(canvas)

            //kreslení hráče(např. jako kruh)
            canvas?.drawCircle(player.x, player.y, 50f, playerPaint)

            //kreslení překážek
            for (obstacle in obstacles) {
                canvas?.drawRect(
                    obstacle.x, obstacle.y,
                    obstacle.x + obstacle.width, obstacle.y + obstacle.height,
                    Paint().apply { color = Color.RED }
                )
            }
            // Kreslení mincí
            for (coin in coins) {
                canvas?.drawCircle(coin.x, coin.y, coin.radius, Paint().apply { color = Color.YELLOW })
            }
            // Zobrazení vzdálenosti
            val textPaint = Paint().apply { color = Color.WHITE; textSize = 50f }
            canvas?.drawText("Distance: $distance", 50f, 200f, textPaint)
        }
        }
    private fun randomYPosition(): Float {
        return (100.height - 100).random().toFloat() //náhodná výška pro nové překážky
    }
}*/