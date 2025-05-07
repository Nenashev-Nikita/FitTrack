package ru.fit.app.shared.profile.domain.repository

import ru.fit.app.shared.profile.domain.entity.UserProfile

interface UserProfileRepository {

	suspend fun get(): UserProfile
}