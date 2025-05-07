package ru.fit.app.features.workout.presentation

import ru.fit.app.shared.training.domain.entity.Training

sealed interface WorkoutState {

	data object Initial : WorkoutState

	data object Loading : WorkoutState

	data object Error : WorkoutState

	data class Content(
		val training: Training,
	) : WorkoutState
}