package ru.fit.app.shared.exercise.domain.usecase

import ru.fit.app.shared.exercise.domain.entity.Exercise
import ru.fit.app.shared.exercise.domain.repository.ExerciseRepository

class GetExerciseUseCase(
	private val repository: ExerciseRepository,
) : suspend (Int) -> Exercise by repository::get