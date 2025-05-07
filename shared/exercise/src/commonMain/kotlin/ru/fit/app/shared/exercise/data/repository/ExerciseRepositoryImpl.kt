package ru.fit.app.shared.exercise.data.repository

import ru.fit.app.shared.exercise.data.network.ExerciseApi
import ru.fit.app.shared.exercise.domain.entity.Exercise
import ru.fit.app.shared.exercise.domain.repository.ExerciseRepository

class ExerciseRepositoryImpl(
	private val exerciseApi: ExerciseApi,
) : ExerciseRepository {

	override suspend fun getAll(): List<Exercise> =
		exerciseApi.getAll()

	override suspend fun get(id: Int): Exercise =
		exerciseApi.get(id)
}