package ru.fit.app.shared.training.domain.usecase

import ru.fit.app.shared.training.domain.entity.Training
import ru.fit.app.shared.training.domain.repository.TrainingRepository

class GetTrainingsUseCase(
	private val trainingRepository: TrainingRepository
) : suspend () -> List<Training> by trainingRepository::getAll