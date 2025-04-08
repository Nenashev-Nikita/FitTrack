package ru.fit.app.shared.training.data.module

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable
import ru.fit.app.shared.training.data.converter.InstantSerializer
import ru.fit.app.shared.training.domain.entity.Exercise

@Serializable
data class TrainingModule(
	val id: Int,
	val name: String,
	@Serializable(with = InstantSerializer::class)
	val date: Instant,
	val exercise: List<Exercise>,
)