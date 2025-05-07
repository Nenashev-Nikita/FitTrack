package ru.fit.app.shared.training.data.converter

import ru.fit.app.shared.training.data.module.TrainingModule
import ru.fit.app.shared.training.domain.entity.Training

class ConverterTraining {

	operator fun invoke(from: List<TrainingModule>): List<Training> =
		from.map { training ->
			Training(
				id = training.id,
				name = training.name,
				date = training.date,
				exercise = training.exercise,
			)
		}
}