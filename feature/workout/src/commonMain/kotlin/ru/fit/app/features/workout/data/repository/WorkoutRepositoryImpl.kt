package ru.fit.app.features.workout.data.repository

import kotlinx.datetime.Instant
import ru.fit.app.features.workout.domain.repository.WorkoutRepository
import ru.fit.app.shared.training.domain.entity.Exercise
import ru.fit.app.shared.training.domain.entity.Training

class WorkoutRepositoryImpl : WorkoutRepository {

	override suspend fun get(id: Int): Training {
		return Training(
			id = 1,
			name = "Тренировка ног",
			date = Instant.parse("2024-06-15T12:00:00Z"),
			exercise = listOf(
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
			),
		)
	}
}