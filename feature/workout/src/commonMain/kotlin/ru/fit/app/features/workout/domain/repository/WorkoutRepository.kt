package ru.fit.app.features.workout.domain.repository

import ru.fit.app.shared.training.domain.entity.Training

interface WorkoutRepository {

	suspend fun get(id: Int): Training
}