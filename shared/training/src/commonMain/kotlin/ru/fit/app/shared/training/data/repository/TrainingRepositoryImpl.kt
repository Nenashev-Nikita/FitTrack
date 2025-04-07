package ru.fit.app.shared.training.data.repository

import ru.fit.app.shared.training.domain.entity.Exercise
import ru.fit.app.shared.training.domain.entity.Training
import ru.fit.app.shared.training.domain.repository.TrainingRepository

class TrainingRepositoryImpl(
//	private val trainingRepositoryApi: TrainingRepositoryApi
) : TrainingRepository {

	override suspend fun getAll(): List<Training> =
		listOf(
			Training(
				id = 1,
				name = "Трениновка ног",
				date = 1000,
				exercise = listOf(
					Exercise(
						name = "",
						approaches = listOf()
					)
				),
			),
			Training(
				id = 2,
				name = "Трениновка рук",
				date = 1000,
				exercise = listOf(
					Exercise(
						name = "",
						approaches = listOf()
					)
				)
			),
		)
}