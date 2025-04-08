package ru.fit.app.shared.training.domain.entity

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class Training(
	val id: Int,
	val name: String,
	val date: Instant,
	val exercise: List<Exercise>,
)