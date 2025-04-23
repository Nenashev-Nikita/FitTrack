package ru.fit.app.presentation

import com.arkivanov.decompose.ComponentContext
import org.kodein.di.DI
import org.kodein.di.direct
import org.kodein.di.factory
import ru.fit.app.features.exercise.details.di.ExerciseArgs
import ru.fit.app.features.exercise.details.presentation.ExerciseComponent
import ru.fit.app.features.exercise.di.MainComponentArgs
import ru.fit.app.features.exercise.presentation.MainComponent
import ru.fit.app.features.exercise.presentation.ProgramComponent
import ru.fit.app.features.profile.di.ProfileComponentArgs
import ru.fit.app.features.profile.presentation.ProfileComponent
import ru.fit.app.features.progress.details.di.DetailsProgressArgs
import ru.fit.app.features.progress.details.presentation.DetailsProgressComponent
import ru.fit.app.features.progress.list.di.ListProgressArgs
import ru.fit.app.features.progress.list.presentation.ListProgressComponent
import ru.fit.app.features.shared.di.FeatureWorkoutArgs
import ru.fit.app.features.shared.presentation.FeatureWorkoutComponent

class ComponentFactory(private val di: DI) {

	fun main(args: MainComponentArgs): MainComponent =
		di.invokeFactory(args)

	fun profile(args: ProfileComponentArgs): ProfileComponent =
		di.invokeFactory(args)

	fun workout(componentContext: ComponentContext, idWorkout: Int): FeatureWorkoutComponent =
		di.invokeFactory(FeatureWorkoutArgs(componentContext, idWorkout))

	fun detailsProgress(componentContext: ComponentContext, onBack: () -> Unit): DetailsProgressComponent =
		di.invokeFactory(DetailsProgressArgs(componentContext, onBack))

	fun listProgress(componentContext: ComponentContext, onDetailsProgress: (Int) -> Unit, onBack: () -> Unit): ListProgressComponent =
		di.invokeFactory(ListProgressArgs(componentContext, onDetailsProgress, onBack))

	fun exercise(componentContext: ComponentContext): ExerciseComponent =
		di.invokeFactory(ExerciseArgs(componentContext))

	fun program(componentContext: ComponentContext): ProgramComponent =
		di.invokeFactory(componentContext)
}

private inline fun <reified A : Any, reified R : Any> DI.invokeFactory(arg: A): R {
	return direct.factory<A, R>().invoke(arg)
}