package dev.johnoreilly.starwars.androidApp.screens

import android.graphics.drawable.Drawable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import dev.johnoreilly.starwars.fragment.FilmFragment


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

    Card(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        elevation = 5.dp,
        shape = RoundedCornerShape(5.dp),

    ) {
        ListItem(
            text = { Text(film.title, style = MaterialTheme.typography.h6) },
            secondaryText = {
                Text(
                    film.director,
                    style = MaterialTheme.typography.subtitle1,
                    color = Color.DarkGray
                )
            }
        )
    }
}



