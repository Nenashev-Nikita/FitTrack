package ru.fit.app.shared.training.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class Approach(
	val weight: Int?,
	val repetitions: Int?,
)
