package ru.fit.app.features.workout.data.converter

import ru.fit.app.shared.training.data.module.TrainingModule
import ru.fit.app.shared.training.domain.entity.Training

class ConverterWorkout {

	operator fun invoke(from: TrainingModule): Training =
		Training(
			id = from.id,
			name = from.name,
			date = from.date,
			exercise = from.exercise,
		)
}