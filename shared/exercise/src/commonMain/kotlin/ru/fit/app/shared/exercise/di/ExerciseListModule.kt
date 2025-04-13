package ru.fit.app.shared.exercise.di

import org.koin.dsl.module
import ru.fit.app.shared.exercise.data.network.ExerciseApi
import ru.fit.app.shared.exercise.data.repository.ExerciseRepositoryImpl
import ru.fit.app.shared.exercise.domain.repository.ExerciseRepository
import ru.fit.app.shared.exercise.domain.usecase.GetExerciseListUseCase
import ru.fit.app.shared.exercise.domain.usecase.GetExerciseUseCase

val ExerciseListModule = module {

	factory { ExerciseApi() }

	factory<ExerciseRepository> { ExerciseRepositoryImpl(get()) }

	factory { GetExerciseUseCase(get()) }
	factory { GetExerciseListUseCase(get()) }
}