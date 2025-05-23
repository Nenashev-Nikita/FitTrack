package ru.fit.app.features.exercise.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.fit.app.design.component.ui.AppBar
import ru.fit.app.design.component.ui.AppBarAction
import ru.fit.app.features.exercise.presentation.MainComponent
import ru.fit.app.features.exercise.presentation.State

@Composable
fun ScreenMain(component: MainComponent) {
	val screenState by component.screenState.collectAsState()

	Column(
		modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
	) {
		AppBar(
			title = "Main",
			leftAction = AppBarAction(
				icon = Icons.Default.Person,
				onClick = component::navigationProfile,
			),
		)

		when (val state = screenState) {
			State.Initial -> {
				LaunchedEffect(Unit) {
					component.loadContent()
				}
			}

			State.Error -> Text("Error")

			State.Loading -> CircularProgressIndicator()

			is State.Content -> {
				Content(
					trainings = state.trainings,
					onWorkoutSelected = component::navigationWorkout,
					onRefresh = {},
					onAddWorkout = {}
				)
			}
		}
	}
}