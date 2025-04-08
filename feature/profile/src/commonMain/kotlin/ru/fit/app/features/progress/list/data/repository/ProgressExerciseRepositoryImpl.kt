package ru.fit.app.features.progress.list.data.repository

import ru.fit.app.features.progress.list.domain.repository.ProgressExerciseRepository
import ru.fit.app.shared.training.domain.entity.Exercise

class ProgressExerciseRepositoryImpl : ProgressExerciseRepository {

	override suspend fun get(): List<Exercise> =
		listOf(
			Exercise(
				id = 1,
				name = "Приседания",
				stepByStepDescription = null,
				img = null,
				approaches = listOf(),
			),
			Exercise(
				id = 2,
				name = "Выпады",
				stepByStepDescription = null,
				img = null,
				approaches = listOf(),
			),
		)
}