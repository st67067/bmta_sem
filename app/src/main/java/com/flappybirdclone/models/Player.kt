class Player(var x: Float = 100f, var y: Float = 300f, var lives: Int = 3, var score: Int = 0) {
val radius: Float = 50f //Poloměr hríče pro kolize
 fun jump(jumpHeight: Float) {
     y -= jumpHeight //Postava poskočí
 }

    fun applyGravity(gravity: Float, groundLevel: Float) {
        y += gravity //Postava padá dolů gravitací
        if (y > groundLevel) y = groundLevel // Zamezení pádu pod zem
    }

    fun collectCoin(coin: Coin): Boolean {
        if (coin.checkCollision(x,y, radius)) {
            score += 1 // zvýšení scóre
            return true
        }
        return false
    }

    fun takeDamage() { //při koloizi ztratí život
        lives -= 1
    }
   /*TODO: Rozhodnout jestli ground move/jump/slide etc. nebo flappy flying
   ----------------------------------------------------------------------
   Mám dilema jestli by postava měla se pohybovat
    po pevné zemi něco jako offline dinosaur od googlu
    nebo flappovat jako flappybird a když hráč nereaguje určitou
    dobu tak bude prostě postava padat gravitací
    */

    /*TODO: HP - and upgrades, resources
    -------------------------------------------------------------------
    napadlo mě že postava měla určitý životy třeba začínala s 2-3 srdíčky
    a v tom obchodu se skiny by se dali kupovat i vylepšení třeba by se dalo
    upgradnout životy (když zbyde čas mohlo by být více skillů a dali by se upgradovat
    během hry by se třeba dali sbírat coiny ( později třeba i více věcí) a za coiny
    koupit skin nebo upgrade

     */








}
