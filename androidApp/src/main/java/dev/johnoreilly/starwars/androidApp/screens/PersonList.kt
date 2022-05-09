package dev.johnoreilly.starwars.androidApp.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import dev.johnoreilly.starwars.fragment.PersonFragment


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
    Card(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        elevation = 5.dp,
        shape = RoundedCornerShape(5.dp)
    ) {
        ListItem(

            text = { Text(person.name, style = MaterialTheme.typography.h6) },
            secondaryText = {
                Text(
                    person.homeworld.name,
                    style = MaterialTheme.typography.subtitle1,
                    color = Color.DarkGray
                )
            },
            icon = {
                Icon(
                    Icons.Filled.Favorite,
                    contentDescription = null,
                    modifier = Modifier.size(40.dp)
                )
            }
        )
    }
}