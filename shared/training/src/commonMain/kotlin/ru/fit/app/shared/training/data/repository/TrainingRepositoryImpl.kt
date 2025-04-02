package ru.fit.app.shared.training.data.repository

import ru.fit.app.shared.training.data.network.TrainingRepositoryApi
import ru.fit.app.shared.training.domain.entity.Training
import ru.fit.app.shared.training.domain.repository.TrainingRepository

class TrainingRepositoryImpl(
	private val trainingRepositoryApi: TrainingRepositoryApi
) : TrainingRepository {

	override suspend fun getAll(): List<Training> =
		trainingRepositoryApi.getAll()
}