package ru.fit.app.shared.profile.domain.usecase

import ru.fit.app.shared.profile.domain.entity.UserProfile
import ru.fit.app.shared.profile.domain.repository.UserProfileRepository

class GetUserProfileUseCase(
	private val repository: UserProfileRepository,
) : suspend () -> UserProfile by repository::get