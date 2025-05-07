package ru.fit.app.shared.profile.di

import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton
import ru.fit.app.shared.profile.data.converter.ConverterUserProfile
import ru.fit.app.shared.profile.data.network.UserProfileApi
import ru.fit.app.shared.profile.data.repository.UserProfileRepositoryImpl
import ru.fit.app.shared.profile.domain.repository.UserProfileRepository
import ru.fit.app.shared.profile.domain.usecase.GetUserProfileUseCase

val UserProfileModule = DI.Module("UserProfileModule") {
	bind<UserProfileApi>() with singleton { UserProfileApi() }
	bind<ConverterUserProfile>() with singleton { ConverterUserProfile() }
	bind<UserProfileRepository>() with singleton { UserProfileRepositoryImpl(instance(), instance()) }
	bind<GetUserProfileUseCase>() with singleton { GetUserProfileUseCase(instance()) }
}