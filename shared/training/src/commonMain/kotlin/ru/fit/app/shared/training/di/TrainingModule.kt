package ru.fit.app.shared.training.di

import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton
import ru.fit.app.shared.training.data.converter.ConverterTraining
import ru.fit.app.shared.training.data.network.TrainingApi
import ru.fit.app.shared.training.data.repository.TrainingRepositoryImpl
import ru.fit.app.shared.training.domain.repository.TrainingRepository
import ru.fit.app.shared.training.domain.usecase.GetTrainingsUseCase

val TrainingModule = DI.Module("TrainingModule") {
	bind { singleton { TrainingApi() } }
	bind { singleton { ConverterTraining() } }
	bind<TrainingRepository> { singleton { TrainingRepositoryImpl(instance(), instance()) } }
	bind { singleton { GetTrainingsUseCase(instance()) } }
}