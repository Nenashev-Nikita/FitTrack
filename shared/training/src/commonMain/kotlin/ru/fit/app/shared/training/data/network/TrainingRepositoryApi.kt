package ru.fit.app.shared.training.data.network

import ru.fit.app.shared.training.domain.entity.Training

interface TrainingRepositoryApi {

	fun getAll(): List<Training> {

		return listOf()
	}
}