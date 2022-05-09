package dev.johnoreilly.starwars.androidApp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.ListItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat.setDecorFitsSystemWindows
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding
import dev.johnoreilly.starwars.androidApp.components.StarWarsBottomNavigation
import dev.johnoreilly.starwars.androidApp.components.StarWarsTopAppBar
import dev.johnoreilly.starwars.androidApp.navigation.Screen
import dev.johnoreilly.starwars.androidApp.navigation.bottomNavigationItems
import dev.johnoreilly.starwars.androidApp.theme.StarWarsTheme
import dev.johnoreilly.starwars.fragment.FilmFragment
import dev.johnoreilly.starwars.fragment.PersonFragment
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
        topBar = { StarWarsTopAppBar("Star Wars") },
        bottomBar = { StarWarsBottomNavigation(navController) }
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



const val PersonListTag = "PersonList"

@Composable
fun PeopleList(people: List<PersonFragment>) {
    LazyColumn(modifier = Modifier.testTag(PersonListTag)) {
        items(people) { person ->
            PersonView(person)
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PersonView(person: PersonFragment) {
    ListItem(
        text = { Text(person.name, style = MaterialTheme.typography.h6) },
        secondaryText = { Text(person.homeworld.name, style = MaterialTheme.typography.subtitle1, color = Color.DarkGray) }
    )
    Divider()
}


const val FilmListTag = "FilmList"

@Composable
fun FilmList(filmList: List<FilmFragment>) {
    LazyColumn(modifier = Modifier.testTag(FilmListTag)) {
        items(items = filmList, itemContent = { film ->
            FilmView(film)
        })
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FilmView(film: FilmFragment) {
    ListItem(
        text = { Text(film.title, style = MaterialTheme.typography.h6) },
        secondaryText = { Text(film.director, style = MaterialTheme.typography.subtitle1, color = Color.DarkGray) }
    )
    Divider()
}