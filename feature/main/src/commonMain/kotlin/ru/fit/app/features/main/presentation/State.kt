package ru.fit.app.features.main.presentation

import ru.fit.app.shared.training.domain.entity.Training

sealed interface State {

	data object Initial : State

	data object Loading : State

	data object Error : State

	data class Content(
		val trainings: List<Training>,
	) : State
}