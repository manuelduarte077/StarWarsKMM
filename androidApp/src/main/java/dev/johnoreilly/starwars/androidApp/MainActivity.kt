package dev.johnoreilly.starwars.androidApp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.core.view.WindowCompat.setDecorFitsSystemWindows
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.ProvideWindowInsets
import dev.johnoreilly.starwars.androidApp.components.StarWarsBottomNavigation
import dev.johnoreilly.starwars.androidApp.components.StarWarsTopAppBar
import dev.johnoreilly.starwars.androidApp.navigation.Screen
import dev.johnoreilly.starwars.androidApp.navigation.Screen.PersonList
import dev.johnoreilly.starwars.androidApp.screens.FilmList
import dev.johnoreilly.starwars.androidApp.screens.PeopleList
import dev.johnoreilly.starwars.androidApp.theme.StarWarsTheme
import dev.johnoreilly.starwars.shared.StarWarsRepository


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


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainLayout() {
    val navController = rememberNavController()
    val repo = rememberStarWarsRepository()

    val people by repo.people.collectAsState(emptyList())
    val filmList by repo.films.collectAsState(emptyList())

    Scaffold(
        topBar = { StarWarsTopAppBar("Star Wars App") },
        bottomBar = { StarWarsBottomNavigation(navController) },
        ) {

        NavHost(navController, startDestination = PersonList.title) {
            composable(PersonList.title) {
                PeopleList(people)
            }
            composable(Screen.FilmList.title) {
                FilmList(filmList)
            }
        }
    }
}

@Composable
private fun rememberStarWarsRepository(): StarWarsRepository {
    val repo = remember { StarWarsRepository() }
    LaunchedEffect(Unit) {
        repo.prefetch()
    }
    return repo
}
