package ru.fit.app.shared.training.data.network

import ru.fit.app.shared.training.domain.entity.Exercise
import ru.fit.app.shared.training.domain.entity.Training

interface TrainingRepositoryApi {

	fun getAll(): List<Training> {

		return listOf(
			Training(
				name = "Трениновка ног",
				date = 1000,
				exercise = Exercise(
					name = "",
					approaches = listOf()
				)
			),
			Training(
				name = "Трениновка рук",
				date = 1000,
				exercise = Exercise(
					name = "",
					approaches = listOf()
				)
			)
		)
	}
}