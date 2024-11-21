package com.flappybirdclone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.flappybirdclone.ui.theme.FlappyBirdCloneTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FlappyBirdCloneTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "menu"
                ) {
                    composable("menu") {
                        MenuScreen(navController)
                    }
                    composable("game") {
                        GameScreen()
                    }
                }
            }
        }
    }
}

@Composable
fun MenuScreen(navController: NavHostController) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        GreetingImage(
            modifier = Modifier.fillMaxSize()
        )
        LogoImage(
            modifier = Modifier.size(300.dp),
            offsetX = 60.dp,
            offsetYOffset = -(350.dp)
        )
        BirdImage(
            modifier = Modifier.size(300.dp),
            offsetX = 60.dp,
            offsetYOffset = -(200.dp)
        )
        PlayButton(
            text = "Start Game",
            modifier = Modifier
                .offset(
                    x = (LocalConfiguration.current.screenWidthDp / 2).dp - 60.dp,
                    y = (LocalConfiguration.current.screenHeightDp * 2 / 3).dp
                ),
            onClick = { navController.navigate("game") }
        )
        Greeting(
            name = "",
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun PlayButton(text: String, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(119, 243, 175),
            contentColor = Color.Black
        ),
        modifier = modifier
    ) {
        Text(text = text)
    }
}
@Composable
fun GreetingImage(modifier: Modifier = Modifier) {
    val image = painterResource(R.drawable.flappy_bg)
    Image(
        painter = image,
        contentDescription = null,
        modifier = modifier.fillMaxSize(),
        contentScale = ContentScale.Crop // Obrázek se ořízne a roztáhne přes plochu
    )
}

@Composable
fun BirdImage(modifier: Modifier = Modifier, offsetX: Dp, offsetYOffset: Dp) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp // Výška obrazovky v Dp

    val totalYOffset = screenHeight / 2 + offsetYOffset // Výška obrazovky + posun Y
    val image = painterResource(R.drawable.bird)

    Image(
        painter = image,
        contentDescription = null,
        modifier = modifier
            .offset(x = offsetX, y = totalYOffset), // Použití výšky obrazovky s přidaným offsetem
        contentScale = ContentScale.Fit
    )
}

@Composable
fun LogoImage(modifier: Modifier = Modifier, offsetX: Dp, offsetYOffset: Dp) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp // Výška obrazovky v Dp

    val totalYOffset = screenHeight / 2 + offsetYOffset // Výška obrazovky + posun Y
    val image = painterResource(R.drawable.flappy_logo)

    Image(
        painter = image,
        contentDescription = null,
        modifier = modifier
            .offset(x = offsetX, y = totalYOffset), // Použití výšky obrazovky s přidaným offsetem
        contentScale = ContentScale.Fit
    )
}
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "$name",
        modifier = modifier
    )
}