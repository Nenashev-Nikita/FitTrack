package ru.fit.app.features.progress.list.presentation

sealed interface State {

	data object Initial : State

	data object Loading : State

	data object Error : State

	data class Content(
		val exercises: List<ExerciseProgress>,
	) : State
}