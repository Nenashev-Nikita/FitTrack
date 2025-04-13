package ru.fit.app.shared.profile.di

import org.koin.dsl.module
import ru.fit.app.shared.profile.data.converter.ConverterUserProfile
import ru.fit.app.shared.profile.data.network.UserProfileApi
import ru.fit.app.shared.profile.data.repository.UserProfileRepositoryImpl
import ru.fit.app.shared.profile.domain.repository.UserProfileRepository
import ru.fit.app.shared.profile.domain.usecase.GetUserProfileUseCase

val UserProfileModule = module {

	factory { UserProfileApi() }

	factory { ConverterUserProfile() }

	factory<UserProfileRepository> { UserProfileRepositoryImpl(get(), get()) }

	factory { GetUserProfileUseCase(get()) }
}