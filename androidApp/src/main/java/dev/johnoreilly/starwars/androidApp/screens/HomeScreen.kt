package dev.johnoreilly.starwars.androidApp.screens

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.johnoreilly.starwars.androidApp.components.StarWarsBottomNavigation
import dev.johnoreilly.starwars.androidApp.components.StarWarsTopAppBar
import dev.johnoreilly.starwars.androidApp.navigation.Screen
import dev.johnoreilly.starwars.shared.StarWarsRepository


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

        NavHost(navController, startDestination = Screen.PersonList.title) {
            composable(Screen.PersonList.title) {
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
