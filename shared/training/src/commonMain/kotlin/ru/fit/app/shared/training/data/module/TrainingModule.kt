package ru.fit.app.shared.training.data.module

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable
import ru.fit.app.shared.exercise.domain.entity.Exercise
import ru.fit.app.shared.util.date.InstantSerializer

@Serializable
data class TrainingModule(
	val id: Int,
	val name: String,
	@Serializable(with = InstantSerializer::class)
	val date: Instant,
	val exercise: List<Exercise>,
)