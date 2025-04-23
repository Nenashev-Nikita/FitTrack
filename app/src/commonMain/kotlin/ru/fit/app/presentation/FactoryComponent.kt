package ru.fit.app.presentation

import com.arkivanov.decompose.ComponentContext
import ru.fit.app.features.exercise.details.presentation.ExerciseComponent
import ru.fit.app.features.exercise.presentation.MainComponent
import ru.fit.app.features.exercise.presentation.MainNavigation
import ru.fit.app.features.exercise.presentation.ProgramComponent
import ru.fit.app.features.profile.presentation.ProfileComponent
import ru.fit.app.features.progress.details.presentation.DetailsProgressComponent
import ru.fit.app.features.progress.list.presentation.ListProgressComponent
import ru.fit.app.features.shared.presentation.FeatureWorkoutComponent

class FactoryComponent(
	private val mainComponentFactory: (ComponentContext, MainNavigation) -> MainComponent,
	private val featureWorkoutComponent: (ComponentContext, Int) -> FeatureWorkoutComponent,
	private val profileComponentFactory: (ComponentContext, () -> Unit, () -> Unit) -> ProfileComponent,
	private val detailsProgressComponentFactory: (ComponentContext, () -> Unit) -> DetailsProgressComponent,
	private val listProgressComponentFactory: (ComponentContext, (Int) -> Unit, () -> Unit) -> ListProgressComponent,
	private val exerciseComponentFactory: (ComponentContext) -> ExerciseComponent,
	private val programComponentFactory: (ComponentContext) -> ProgramComponent,
) {

	fun main(
		componentContext: ComponentContext,
		mainNavigation: MainNavigation,
	): MainComponent =
		mainComponentFactory(componentContext, mainNavigation)

	fun workout(
		componentContext: ComponentContext,
		idWorkout: Int,
	): FeatureWorkoutComponent =
		featureWorkoutComponent(componentContext, idWorkout)

	fun profile(
		componentContext: ComponentContext,
		onBack: () -> Unit,
		onListProgress: () -> Unit,
	): ProfileComponent =
		profileComponentFactory(componentContext, onBack, onListProgress)

	fun detailsProgress(
		componentContext: ComponentContext,
		onBack: () -> Unit,
	): DetailsProgressComponent =
		detailsProgressComponentFactory(componentContext, onBack)

	fun listProgress(
		componentContext: ComponentContext,
		onDetailsProgress: (Int) -> Unit,
		onBack: () -> Unit,
	): ListProgressComponent =
		listProgressComponentFactory(componentContext, onDetailsProgress, onBack)

	fun exercise(
		componentContext: ComponentContext,
	): ExerciseComponent =
		exerciseComponentFactory(componentContext)

	fun program(
		componentContext: ComponentContext,
	): ProgramComponent =
		programComponentFactory(componentContext)
}