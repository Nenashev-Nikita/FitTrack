package ru.fit.app.shared.training.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class Exercise(
	val id: Int,
	val name: String,
	val stepByStepDescription: List<String>?,
	val img: String?,
	val approaches: List<Approach>?,
)
