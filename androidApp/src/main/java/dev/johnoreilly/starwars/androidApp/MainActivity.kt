package dev.johnoreilly.starwars.androidApp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat.setDecorFitsSystemWindows
import com.google.accompanist.insets.ProvideWindowInsets
import dev.johnoreilly.starwars.androidApp.screens.MainLayout
import dev.johnoreilly.starwars.androidApp.theme.StarWarsTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setDecorFitsSystemWindows(window, false)

        setContent {
            StarWarsTheme {
                ProvideWindowInsets {
                    MainLayout()
                }
            }
        }
    }
}

