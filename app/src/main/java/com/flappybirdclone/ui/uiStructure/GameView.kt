class GameView(context: Context, attrs: AttributeSets) : View(context, attrs) {

    //Barvy pro vykreslování (pouze prozatím)
    private val playerPaint = Paint().apply { color = Color.BLUE }

    private var player = Player(100f, height /2f)

    //Aktualizace stavu hry - bude se volat v herní smyčce
    private val gameLoop = Runnable {
        update()
        invalidate() //Znovu překreslí obrazovku
        postDelayed(gameLoop, 16) //Smyčka obnovuje obrazovku zhruba každých 16ms (-60FPS)
    }

    init {
        startGame()

        //Spuštění hry
        private fun startGame() {
            post(gameLoop) //spustí herní smyčku (asi)
        }

        private fun update() {
            player.update()
        }

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
        }
    }

}