package com.flappybirdclone

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlin.random.Random

@Composable
fun GameScreen() {

    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    // Výška bird
    val birdSize = 150.dp

    // Stav pro vertikální pozici bird
    var birdOffsetY by remember { mutableStateOf(screenHeight / 2 - birdSize / 2) }
    val gravity = 6.dp // Síla gravitace
    val jumpHeight = 160.dp // Výška skoku

    // Stav pro pozice pipes
    var pipesList by remember { mutableStateOf(mutableListOf<Pipe>()) }
    var isGameOver by remember { mutableStateOf(false) }

    fun restartGame() {
        birdOffsetY = screenHeight / 2 - birdSize / 2
        pipesList = mutableListOf()
        isGameOver = false
    }

    // Spuštění pohybu vlivem gravitace
    LaunchedEffect(Unit) {
        val pipeWidth = 400.dp // Šířka pipes
        while (true) {
            delay(16) // ~60 fps
            birdOffsetY += gravity
            //if (birdOffsetY > screenHeight - birdSize) birdOffsetY = screenHeight - birdSize
            //if (birdOffsetY < 0.dp) birdOffsetY = 0.dp

            // Posun pipes a odstranění těch mimo obrazovku
            pipesList = pipesList.map { it.copy(offsetX = it.offsetX - 5.dp) }
                .filter { it.offsetX > - pipeWidth }.toMutableList()

            // Přidání nového pipes
            if (pipesList.isEmpty() || pipesList.last().offsetX < screenWidth - 300.dp) {
                pipesList = (pipesList + Pipe(
                    offsetX = screenWidth,
                    gapOffsetY = Random.nextInt((screenHeight.value * 0.2).toInt(), (screenHeight.value * 0.8).toInt()).dp
                )).toMutableList()
            }

            // Detekce kolizí
            val birdRect = Rect(
                x = 0.dp,
                y = birdOffsetY,
                width = birdSize,
                height = birdSize
            )

            for (pipe in pipesList) {
                val pipeWidth = 100.dp
                val upperPipeRect = Rect(
                    x = pipe.offsetX,
                    y = 0.dp,
                    width = pipeWidth,
                    height = pipe.gapOffsetY
                )

                val lowerPipeRect = Rect(
                    x = pipe.offsetX,
                    y = pipe.gapOffsetY + 150.dp,
                    width = pipeWidth,
                    height = screenHeight
                )

                /* TODO upravit kolize */
                /*if (birdRect.intersects(upperPipeRect) || birdRect.intersects(lowerPipeRect)) {
                    isGameOver = true
                    break
                }*/
            }

            // Omezení, aby bird nespadl mimo obrazovku
            /*if (birdOffsetY > screenHeight - birdSize) {
                birdOffsetY = screenHeight - birdSize
            } else if (birdOffsetY < 0.dp) {
                birdOffsetY = 0.dp
            }*/
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        birdOffsetY -= jumpHeight // Bird vyskočí nahoru
                        //if (birdOffsetY < 0.dp) birdOffsetY = 0.dp // Zamezení, aby nevyskočil mimo obrazovku
                    }
                )
            }
    ) {
        // Pozadí
        Image(
            painter = painterResource(R.drawable.flappy_bg),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Pipes
        pipesList.forEach { pipe ->
            PipeImage(pipe.offsetX, pipe.gapOffsetY, screenHeight)
        }

        // Bird
        if (!isGameOver) {
            Image(
                painter = painterResource(R.drawable.bird),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .offset(y = birdOffsetY)
                    .size(birdSize),
                contentScale = ContentScale.Fit
            )
        } else {
            // Game Over tlačítko
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0x88000000)), // Poloprůhledné pozadí
                contentAlignment = Alignment.Center
            ) {
                Button(onClick = { restartGame() }) {
                    Text("Game Over - Restart")
                }
            }
        }
    }
}

@Composable
fun PipeImage(offsetX: Dp, gapOffsetY: Dp, screenHeight: Dp, gapSize: Dp = 150.dp) {
    val pipeSize = 550.dp
    val verticalOffset = screenHeight / 3

    // Horní část pipe
    Image(
        painter = painterResource(R.drawable.pipe),
        contentDescription = null,
        modifier = Modifier
            .offset(x = offsetX, y = gapOffsetY + gapSize - pipeSize - verticalOffset)
            .size(pipeSize)
            .graphicsLayer(rotationX = 180f), // Otočení o 180°
        contentScale = ContentScale.Fit
    )

    // Spodní část pipe
    Image(
        painter = painterResource(R.drawable.pipe),
        contentDescription = null,
        modifier = Modifier
            .offset(x = offsetX, y = gapOffsetY + pipeSize - verticalOffset)
            .size(pipeSize),
        contentScale = ContentScale.Fit
    )
}
data class Pipe(
    val offsetX: Dp,
    val gapOffsetY: Dp
)
// Datová třída Rect pro hranice
data class Rect(val x: Dp, val y: Dp, val width: Dp, val height: Dp) {
    fun intersects(other: Rect): Boolean {
        return x < other.x + other.width &&
                x + width > other.x &&
                y < other.y + other.height &&
                y + height > other.y
    }
}