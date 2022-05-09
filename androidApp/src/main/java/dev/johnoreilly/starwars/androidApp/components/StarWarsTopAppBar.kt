package dev.johnoreilly.starwars.androidApp.components

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.statusBarsPadding

@Composable
fun StarWarsTopAppBar(title: String) {
    Surface(color = MaterialTheme.colors.primary) {
        TopAppBar(
            title = { Text(title) },
            backgroundColor = Color.Transparent,
            elevation = 0.dp,
            modifier = Modifier.statusBarsPadding()
        )
    }
}