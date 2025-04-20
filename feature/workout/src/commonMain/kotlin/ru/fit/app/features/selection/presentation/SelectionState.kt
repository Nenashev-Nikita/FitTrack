package ru.fit.app.features.selection.presentation

import ru.fit.app.shared.training.domain.entity.Training

sealed interface SelectionState {

	data object Initial : SelectionState

	data object Loading : SelectionState

	data object Error : SelectionState

	data class Content(
		val training: Training,
	) : SelectionState
}