package ru.fit.app.shared.exercise.di

import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton
import ru.fit.app.shared.exercise.data.network.ExerciseApi
import ru.fit.app.shared.exercise.data.repository.ExerciseRepositoryImpl
import ru.fit.app.shared.exercise.domain.repository.ExerciseRepository
import ru.fit.app.shared.exercise.domain.usecase.GetExerciseListUseCase
import ru.fit.app.shared.exercise.domain.usecase.GetExerciseUseCase

val ExerciseListModule = DI.Module("ExerciseListModule") {
	bind<ExerciseApi>() with singleton { ExerciseApi() }
	bind<ExerciseRepository>() with singleton { ExerciseRepositoryImpl(instance()) }
	bind<GetExerciseUseCase>() with singleton { GetExerciseUseCase(instance()) }
	bind<GetExerciseListUseCase>() with singleton { GetExerciseListUseCase(instance()) }
}