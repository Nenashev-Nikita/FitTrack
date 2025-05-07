package ru.fit.app.shared.profile.data.repository

import ru.fit.app.shared.profile.data.converter.ConverterUserProfile
import ru.fit.app.shared.profile.data.network.UserProfileApi
import ru.fit.app.shared.profile.domain.entity.UserProfile
import ru.fit.app.shared.profile.domain.repository.UserProfileRepository

class UserProfileRepositoryImpl(
	private val userProfileApi: UserProfileApi,
	private val converterUserProfile: ConverterUserProfile,
) : UserProfileRepository {

	override suspend fun get(): UserProfile =
		converterUserProfile(userProfileApi.get())
}