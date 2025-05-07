package ru.fit.app.shared.profile.domain.entity

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data class UserProfile(
	val userId: Int,
	val username: String,
	val firstName: String,
	val lastName: String,
	val email: String,
	val phone: String? = null,
	val avatarUrl: String? = null,
	val birthDate: LocalDate? = null,
	val height: Double? = null,
	val weight: Double? = null,
	val fitnessGoals: List<String> = emptyList(),
	val activityLevel: ActivityLevel = ActivityLevel.MODERATE,
	val preferredWorkoutTypes: List<WorkoutType> = emptyList(),
	val registrationDate: Instant,
	val notificationSettings: NotificationSettings = NotificationSettings(),
	val socialMediaLinks: Map<String, String> = emptyMap(),
)