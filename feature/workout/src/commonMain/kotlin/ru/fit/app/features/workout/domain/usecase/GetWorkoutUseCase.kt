package ru.fit.app.features.workout.domain.usecase

import ru.fit.app.features.workout.domain.repository.WorkoutRepository
import ru.fit.app.shared.training.domain.entity.Training

class GetWorkoutUseCase(
	private val workoutRepository: WorkoutRepository
) : suspend (Int) -> Training by workoutRepository::get