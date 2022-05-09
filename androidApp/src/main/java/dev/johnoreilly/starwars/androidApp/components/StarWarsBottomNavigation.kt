package dev.johnoreilly.starwars.androidApp.components

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.insets.navigationBarsPadding
import dev.johnoreilly.starwars.androidApp.navigation.bottomNavigationItems

@Composable
fun StarWarsBottomNavigation(navController: NavHostController) {

    BottomNavigation(modifier = Modifier.navigationBarsPadding()) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        bottomNavigationItems.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        painterResource(item.icon),
                        contentDescription = item.iconContentDescription
                    )
                },
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.id)
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}