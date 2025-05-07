package ru.fit.app.shared.profile.data.module

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable
import ru.fit.app.shared.profile.domain.entity.ActivityLevel
import ru.fit.app.shared.profile.domain.entity.NotificationSettings
import ru.fit.app.shared.profile.domain.entity.WorkoutType
import ru.fit.app.shared.util.date.InstantSerializer

@Serializable
data class UserProfileModule(
	val userId: Int,
	val username: String,
	val firstName: String,
	val lastName: String,
	val email: String,
	val phone: String? = null,
	val avatarUrl: String? = null,
//	@Serializable(with = LocalDateSerializer::class)
	val birthDate: LocalDate?,
	val height: Double? = null,
	val weight: Double? = null,
	val fitnessGoals: List<String> = emptyList(),
	val activityLevel: ActivityLevel = ActivityLevel.MODERATE,
	val preferredWorkoutTypes: List<WorkoutType> = emptyList(),
	@Serializable(with = InstantSerializer::class)
	val registrationDate: Instant,
	val notificationSettings: NotificationSettings = NotificationSettings(),
	val socialMediaLinks: Map<String, String> = emptyMap(),
)