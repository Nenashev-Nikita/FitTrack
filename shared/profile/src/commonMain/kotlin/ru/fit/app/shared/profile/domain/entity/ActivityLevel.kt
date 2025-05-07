package ru.fit.app.shared.profile.domain.entity

import kotlinx.serialization.Serializable

@Serializable
enum class ActivityLevel {

	SEDENTARY,
	LIGHT,
	MODERATE,
	ACTIVE,
	VERY_ACTIVE
}