package ru.fit.app.shared.exercise.domain.repository

import ru.fit.app.shared.exercise.domain.entity.Exercise

interface ExerciseRepository {

	suspend fun getAll(): List<Exercise>

	suspend fun get(id: Int): Exercise
}