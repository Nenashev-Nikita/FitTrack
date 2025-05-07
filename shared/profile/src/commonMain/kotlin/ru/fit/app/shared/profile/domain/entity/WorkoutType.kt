package ru.fit.app.shared.profile.domain.entity

import kotlinx.serialization.Serializable

@Serializable
enum class WorkoutType {

	STRENGTH_TRAINING,
	CARDIO,
	YOGA,
	CROSSFIT,
	MOBILITY,
	ENDURANCE
}