package ru.fit.app.shared.exercise.domain.usecase

import ru.fit.app.shared.exercise.domain.entity.Exercise
import ru.fit.app.shared.exercise.domain.repository.ExerciseRepository

class GetExerciseListUseCase(
	private val repository: ExerciseRepository,
) : suspend () -> List<Exercise> by repository::getAll