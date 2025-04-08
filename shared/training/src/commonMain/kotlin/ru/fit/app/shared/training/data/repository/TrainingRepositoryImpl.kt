package ru.fit.app.shared.training.data.repository

import ru.fit.app.shared.training.data.converter.ConverterTraining
import ru.fit.app.shared.training.data.network.TrainingApi
import ru.fit.app.shared.training.domain.entity.Training
import ru.fit.app.shared.training.domain.repository.TrainingRepository

class TrainingRepositoryImpl(
	private val trainingApi: TrainingApi,
	private val converterTraining: ConverterTraining,
) : TrainingRepository {

	override suspend fun getAll(): List<Training> {
		val training = trainingApi.getAll()

		return converterTraining(training)
	}
}