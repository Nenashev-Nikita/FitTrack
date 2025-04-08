package ru.fit.app.shared.training.di

import org.koin.dsl.module
import ru.fit.app.shared.training.data.converter.ConverterTraining
import ru.fit.app.shared.training.data.network.TrainingApi
import ru.fit.app.shared.training.data.repository.TrainingRepositoryImpl
import ru.fit.app.shared.training.domain.repository.TrainingRepository
import ru.fit.app.shared.training.domain.usecase.GetTrainingsUseCase

val TrainingModule = module {

	factory { TrainingApi() }

	factory { ConverterTraining() }

	factory<TrainingRepository> { TrainingRepositoryImpl(get(), get()) }

	factory { GetTrainingsUseCase(get()) }
}