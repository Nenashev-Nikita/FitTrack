package ru.fit.app.features.details.presentation

import ru.fit.app.shared.training.domain.entity.Training

sealed interface DetailsState {

	data object Initial : DetailsState

	data object Loading : DetailsState

	data object Error : DetailsState

	data class Content(
		val training: Training,
	) : DetailsState
}