package ru.fit.app.features.workout.data.repository

import ru.fit.app.features.workout.data.converter.ConverterWorkout
import ru.fit.app.features.workout.data.network.WorkoutApi
import ru.fit.app.features.workout.domain.repository.WorkoutRepository
import ru.fit.app.shared.training.domain.entity.Training

class WorkoutRepositoryImpl(
	private val workoutApi: WorkoutApi,
	private val converterWorkout: ConverterWorkout,
) : WorkoutRepository {

	override suspend fun get(id: Int): Training =
		converterWorkout(workoutApi.get(id))
}