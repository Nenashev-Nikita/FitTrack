package ru.fit.app.features.progress.list.presentation

import ru.fit.app.shared.training.domain.entity.Exercise

sealed interface State {

	data object Initial : State

	data object Loading : State

	data object Error : State

	data class Content(
		val exercises: List<Exercise>,
	) : State
}