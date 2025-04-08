package ru.fit.app.features.progress.list.domain.usecase

import ru.fit.app.features.progress.list.domain.repository.ProgressExerciseRepository
import ru.fit.app.shared.training.domain.entity.Exercise

class GetProgressExerciseUseCase(
	private val repository: ProgressExerciseRepository
) : suspend () -> List<Exercise> by repository::get