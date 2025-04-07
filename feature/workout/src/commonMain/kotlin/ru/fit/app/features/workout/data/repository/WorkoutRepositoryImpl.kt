package ru.fit.app.features.workout.data.repository

import ru.fit.app.features.workout.domain.repository.WorkoutRepository
import ru.fit.app.shared.training.domain.entity.Approach
import ru.fit.app.shared.training.domain.entity.Exercise
import ru.fit.app.shared.training.domain.entity.Training

class WorkoutRepositoryImpl : WorkoutRepository {

	override suspend fun get(id: Int): Training {
		return Training(
			id = 1,
			name = "Тренировка ног",
			date = 100L,
			exercise = listOf(
				Exercise(
					name = "Приседания",
					approaches = listOf(
						Approach(
							weight = 2,
							repetitions = 8,
						)
					),
				),
				Exercise(
					name = "Сальто назад",
					approaches = listOf(
						Approach(
							weight = 5,
							repetitions = 5,
						)
					),
				)
			),
		)
	}
}