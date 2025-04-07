package ru.fit.app.features.main.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.fit.app.features.main.presentation.MainComponent
import ru.fit.app.features.main.presentation.State

@Composable
fun ScreenMain(component: MainComponent) {
	val screenState by component.screenState.collectAsState()

	MaterialTheme {
		Column(
			modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
		) {
			when (val state = screenState) {
				State.Initial    -> {
					LaunchedEffect(Unit) {
						component.loadContent()
					}
				}

				State.Error      -> Text("Error")

				State.Loading    -> CircularProgressIndicator()

				is State.Content -> {
					Content(
						trainings = state.trainings,
						onWorkoutSelected = component::navigationWorkout
					)
				}
			}
		}
	}
}