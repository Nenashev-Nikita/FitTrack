package ru.fit.app.features.progress.list.domain.repository

import ru.fit.app.shared.training.domain.entity.Exercise

interface ProgressExerciseRepository {

	suspend fun get(): List<Exercise>
}