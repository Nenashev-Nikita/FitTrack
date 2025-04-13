package ru.fit.app.features.progress.list.data.repository

import ru.fit.app.features.progress.list.domain.repository.ProgressExerciseRepository
import ru.fit.app.shared.exercise.domain.entity.Exercise

class ProgressExerciseRepositoryImpl : ProgressExerciseRepository {

	override suspend fun get(): List<Exercise> =
		listOf()
}