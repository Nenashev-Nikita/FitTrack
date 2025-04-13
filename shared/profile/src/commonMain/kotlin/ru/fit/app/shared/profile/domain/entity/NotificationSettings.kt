package ru.fit.app.shared.profile.domain.entity

import kotlinx.datetime.LocalTime
import kotlinx.serialization.Serializable

@Serializable
data class NotificationSettings(
	val workoutReminders: Boolean = true,
	val progressUpdates: Boolean = true,
	val newFeatures: Boolean = true,
	val emailNotifications: Boolean = false,
	val pushNotifications: Boolean = true,
	val notificationTime: LocalTime? = null,
)