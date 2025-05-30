package ru.fit.app.features.progress.list.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.fit.app.features.progress.list.presentation.ListProgressComponent
import ru.fit.app.features.progress.list.presentation.State
import ru.fit.app.shared.exercise.domain.entity.Exercise

@Composable
fun ScreenListProgress(component: ListProgressComponent) {
	val screenState by component.screenState.collectAsState()

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
					progress = state.exercises,
					modifier = Modifier,
					exercise = Exercise(
						id = 1,
						name = "Жим лежа",
						type = Exercise.ExerciseType.STRENGTH,
						targetMuscles = listOf("Грудь", "Трицепс"),
						equipment = Exercise.EquipmentType.BARBELL,
						difficulty = Exercise.DifficultyLevel.INTERMEDIATE
					)
				)
			}
		}
	}
}