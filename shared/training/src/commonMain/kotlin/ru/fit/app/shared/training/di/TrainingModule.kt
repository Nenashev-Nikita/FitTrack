package ru.fit.app.shared.training.di

import org.koin.dsl.module
import ru.fit.app.shared.training.data.repository.TrainingRepositoryImpl
import ru.fit.app.shared.training.domain.repository.TrainingRepository
import ru.fit.app.shared.training.domain.usecase.GetTrainingsUseCase

val TrainingModule = module {

	factory<TrainingRepository> { TrainingRepositoryImpl(get()) }

	factory { GetTrainingsUseCase(get()) }
}