package ru.fit.app.features.progress.list.data.repository

import ru.fit.app.features.progress.list.domain.repository.ProgressExerciseRepository
import ru.fit.app.shared.training.domain.entity.Exercise

class ProgressExerciseRepositoryImpl : ProgressExerciseRepository {

	override suspend fun get(): List<Exercise> =
		listOf(
			Exercise(
				name = "Приседания",
				approaches = listOf(

				)
			),
			Exercise(
				name = "Выпады",
				approaches = listOf(

				)
			)
		)
}