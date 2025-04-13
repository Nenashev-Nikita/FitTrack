package ru.fit.app.features.exercise.details.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsRun
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.SelfImprovement
import androidx.compose.material.icons.filled.Sports
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Badge
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import ru.fit.app.shared.exercise.domain.entity.Exercise

@Composable
fun Content(
	exercises: List<Exercise>,
	onExerciseClick: (Int) -> Unit,
	modifier: Modifier = Modifier,
) {
	val searchQuery by remember { mutableStateOf("") }
	val selectedType by remember { mutableStateOf<Exercise.ExerciseType?>(null) }

	Column(modifier = modifier.fillMaxSize()) {
//		SearchBar(
//			modifier = Modifier.padding(16.dp),
//			inputField = {},
//			expanded = true,
//			onExpandedChange = {  },
//		) {
//
//		}

//		ExerciseTypeFilter(
//			selectedType = selectedType,
//			onTypeSelected = { selectedType = it },
//			modifier = Modifier.padding(horizontal = 16.dp)
//		)

		LazyColumn(
//			contentPadding = PaddingValues(16.dp),
			verticalArrangement = Arrangement.spacedBy(8.dp)
		) {
			items(
				items = exercises
					.filter { it.type == selectedType || selectedType == null }
					.filter { it.name.contains(searchQuery, true) },
				key = { it.id }
			) { exercise ->
				ExerciseItem(
					exercise = exercise,
					onClick = { onExerciseClick(exercise.id) }
				)
			}
		}
	}
}

@Composable
private fun ExerciseItem(
	exercise: Exercise,
	onClick: () -> Unit,
	modifier: Modifier = Modifier,
) {
	Card(
		onClick = onClick,
		modifier = modifier.fillMaxWidth(),
		elevation = CardDefaults.cardElevation(4.dp)
	) {
		Row(
			modifier = Modifier.padding(16.dp),
			verticalAlignment = Alignment.CenterVertically
		) {
//			ExerciseImage(
//				imageUrl = exercise.img,
//				modifier = Modifier.size(100.dp)
//			)

			Spacer(Modifier.width(16.dp))

			Column(Modifier.weight(1f)) {
				Text(
					text = exercise.name,
					style = MaterialTheme.typography.titleLarge,
					maxLines = 1,
					overflow = TextOverflow.Ellipsis
				)

				ExerciseTypeChip(type = exercise.type)

				exercise.difficulty?.let {
					DifficultyBadge(difficulty = it)
				}

				Text(
					text = exercise.targetMuscles?.joinToString() ?: "",
					style = MaterialTheme.typography.bodyMedium,
					color = MaterialTheme.colorScheme.onSurfaceVariant,
					maxLines = 2,
					overflow = TextOverflow.Ellipsis
				)
			}
		}
	}
}

@Composable
private fun ExerciseTypeChip(type: Exercise.ExerciseType) {
	val (text, icon) = when (type) {
		Exercise.ExerciseType.STRENGTH    -> "Сила" to Icons.Default.FitnessCenter
		Exercise.ExerciseType.CARDIO      -> "Кардио" to Icons.Default.DirectionsRun
		Exercise.ExerciseType.FLEXIBILITY -> "Гибкость" to Icons.Default.SelfImprovement
		else                              -> "Другое" to Icons.Default.Sports
	}

	AssistChip(
		onClick = {},
		label = { Text(text) },
		leadingIcon = {
			Icon(
				imageVector = icon,
				contentDescription = null
			)
		}
	)
}

@Composable
private fun DifficultyBadge(difficulty: Exercise.DifficultyLevel) {
	val color = when (difficulty) {
		Exercise.DifficultyLevel.BEGINNER     -> Color.Green
		Exercise.DifficultyLevel.INTERMEDIATE -> Color.Yellow
		Exercise.DifficultyLevel.ADVANCED     -> Color.Magenta
		Exercise.DifficultyLevel.ATHLETE      -> Color.Red
	}

	Badge(
		containerColor = color.copy(alpha = 0.2f),
		contentColor = color
	) {
		Text(
			text = difficulty.name,
			style = MaterialTheme.typography.labelSmall
		)
	}
}

@Composable
private fun ExerciseImage(imageUrl: String?, modifier: Modifier = Modifier) {
//	AsyncImage(
//		model = imageUrl,
//		contentDescription = null,
//		modifier = modifier.clip(RoundedCornerShape(8.dp)),
//		placeholder = painterResource(R.drawable.placeholder_exercise),
//		error = painterResource(R.drawable.placeholder_exercise)
//	)
}