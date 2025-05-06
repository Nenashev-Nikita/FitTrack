package ru.fit.app.features.exercise.details.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.fit.app.FitTheme
import ru.fit.app.design.component.ui.AppBar
import ru.fit.app.design.component.ui.AppBarAction
import ru.fit.app.design.component.ui.ChipItem
import ru.fit.app.design.component.ui.HorizontalChipsList
import ru.fit.app.design.component.ui.SearchBar
import ru.fit.app.shared.exercise.domain.entity.Exercise

@Composable
fun Content(
	exercises: List<Exercise>,
	onExerciseClick: (Int) -> Unit,
	modifier: Modifier = Modifier,
) {
	val searchQuery by remember { mutableStateOf("") }
	val selectedType by remember { mutableStateOf<Exercise.ExerciseType?>(null) }
	val listState = rememberLazyListState()
	val isScrollingUp = listState.isScrollingUp()
	Box(modifier = modifier.fillMaxSize()) {
		Column {
			AppBar(
				rightActions = listOf(
					AppBarAction(
						icon = Icons.Default.FilterList,
						onClick = {}
					)
				)
			)

			SearchBar(
				query = "Введите название...",
				onQueryChange = {}
			)

			HorizontalChipsList(
				iconChip = ChipItem(
					icon = Icons.Default.Favorite,
					isSelected = false,
					onClick = {}
				),
				textChips = listOf(
					ChipItem(
						text = "Cardio",
						isSelected = false,
						onClick = {}
					),
					ChipItem(
						text = "Strength",
						isSelected = true,
						onClick = {}
					),
					ChipItem(
						text = "Yoga",
						isSelected = false,
						onClick = {}
					),
					ChipItem(
						text = "Cardio",
						isSelected = false,
						onClick = {}
					),
					ChipItem(
						text = "Strength",
						isSelected = true,
						onClick = {}
					),
					ChipItem(
						text = "Yoga",
						isSelected = false,
						onClick = {}
					),
					ChipItem(
						text = "Cardio",
						isSelected = false,
						onClick = {}
					),
					ChipItem(
						text = "Strength",
						isSelected = true,
						onClick = {}
					),
					ChipItem(
						text = "Yoga",
						isSelected = false,
						onClick = {}
					),
				),
			)

		LazyColumn(
			contentPadding = PaddingValues(16.dp),
			verticalArrangement = Arrangement.spacedBy(8.dp),
			state = listState,
		) {
			items(
				items = exercises
					.filter { it.type == selectedType || selectedType == null }
					.filter { it.name.contains(searchQuery, true) },
				key = { it.id }
			) { exercise ->
				ExerciseCard(
					exercise = exercise,
					onClick = { onExerciseClick(exercise.id) }
				)
			}
		}
		}
		AnimatedVisibility(
			visible = isScrollingUp || !listState.canScrollForward && !listState.canScrollBackward,
			enter = fadeIn() + slideInVertically { it },
			exit = fadeOut() + slideOutVertically { it },
			modifier = Modifier
				.align(Alignment.BottomEnd)
				.padding(16.dp)
		) {
			FloatingActionButton(
				onClick = {},
				containerColor = FitTheme.colors.permanentPrimary,
				contentColor = FitTheme.colors.fondInvert
			) {
				Icon(
					imageVector = Icons.Default.Add,
					contentDescription = "Add exercise"
				)
			}
		}
	}
}

@Composable
private fun LazyListState.isScrollingUp(): Boolean {
	var previousIndex by remember(this) { mutableStateOf(firstVisibleItemIndex) }
	var previousScrollOffset by remember(this) { mutableStateOf(firstVisibleItemScrollOffset) }
	return remember(this) {
		derivedStateOf {
			if (previousIndex != firstVisibleItemIndex) {
				previousIndex > firstVisibleItemIndex
			} else {
				previousScrollOffset >= firstVisibleItemScrollOffset
			}.also {
				previousIndex = firstVisibleItemIndex
				previousScrollOffset = firstVisibleItemScrollOffset
			}
		}
	}.value
}