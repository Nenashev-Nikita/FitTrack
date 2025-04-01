package ru.fit.app.shared.training.domain.repository

import ru.fit.app.shared.training.domain.entity.Training

interface TrainingRepository {

	suspend fun getAll(): List<Training>
}