package ru.fit.app.shared.profile.data.converter

import ru.fit.app.shared.profile.data.module.UserProfileModule
import ru.fit.app.shared.profile.domain.entity.UserProfile

class ConverterUserProfile {

	operator fun invoke(user: UserProfileModule): UserProfile =
		UserProfile(
			userId = user.userId,
			username = user.username,
			firstName = user.firstName,
			lastName = user.lastName,
			email = user.email,
			phone = user.phone,
			avatarUrl = user.avatarUrl,
			birthDate = user.birthDate,
			height = user.height,
			weight = user.weight,
			fitnessGoals = user.fitnessGoals,
			activityLevel = user.activityLevel,
			preferredWorkoutTypes = user.preferredWorkoutTypes,
			registrationDate = user.registrationDate,
			notificationSettings = user.notificationSettings,
			socialMediaLinks = user.socialMediaLinks,
		)
}