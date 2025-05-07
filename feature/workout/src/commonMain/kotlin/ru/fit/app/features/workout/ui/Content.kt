package ru.fit.app.features.workout.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.fit.app.shared.exercise.domain.entity.Exercise
import ru.fit.app.shared.training.domain.entity.Training

@Composable
fun Content(
	modifier: Modifier = Modifier,
	training: Training,
	onSelectionClick: () -> Unit,
	onDetailsClick: () -> Unit,
) {
	val lazyListState = rememberLazyListState()

	Text(
		text = training.name,
	)

	Spacer(Modifier.height(16.dp))

	LazyColumn(
		modifier = modifier,
		state = lazyListState,
		contentPadding = PaddingValues(16.dp),
		verticalArrangement = Arrangement.spacedBy(8.dp)
	) {
		items(training.exercise) { exercise ->
			ExerciseCard(
				exercise = exercise,
				onDetailsClick = onDetailsClick,
			)
		}
	}

	Button(
		onClick = {
			onSelectionClick()
		}
	) {
		Text("Selection")
	}
}

@Composable
fun ExerciseCard(
	exercise: Exercise,
	onDetailsClick: () -> Unit,
) {
	Card(
		modifier = Modifier
			.fillMaxWidth()
			.clickable { onDetailsClick() },
	) {
		Row(
			modifier = Modifier
				.padding(16.dp)
				.fillMaxWidth(),
			verticalAlignment = Alignment.CenterVertically
		) {
			Text(
				text = exercise.name,
			)
		}
	}
}