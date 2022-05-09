package dev.johnoreilly.starwars.androidApp.navigation

import dev.johnoreilly.starwars.androidApp.R

sealed class Screen(val title: String) {
    object PersonList : Screen("Person List")
    object FilmList : Screen("Film List")
}

data class BottomNavigationitem(val route: String, val icon: Int, val iconContentDescription: String)

val bottomNavigationItems = listOf(
    BottomNavigationitem(Screen.PersonList.title, R.drawable.ic_face, Screen.PersonList.title),
    BottomNavigationitem(Screen.FilmList.title, R.drawable.ic_movie, Screen.FilmList.title)
)
