package ru.fit.app.features.exercise.details.presentation

import ru.fit.app.shared.exercise.domain.entity.Exercise

sealed interface State {

	data object Initial : State

	data object Loading : State

	data object Error : State

	data class Content(
		val exercises: List<Exercise>,
	) : State
}