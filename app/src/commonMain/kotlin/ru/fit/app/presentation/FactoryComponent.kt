package ru.fit.app.presentation

import com.arkivanov.decompose.ComponentContext
import ru.fit.app.features.main.presentation.MainComponent
import ru.fit.app.features.profile.presentation.ProfileComponent
import ru.fit.app.features.progress.details.presentation.DetailsProgressComponent
import ru.fit.app.features.progress.list.presentation.ListProgressComponent
import ru.fit.app.features.workout.presentation.WorkoutComponent

class FactoryComponent(
	private val mainComponentFactory: (ComponentContext, (Int) -> Unit, () -> Unit) -> MainComponent,
	private val workoutComponentFactory: (ComponentContext, Int) -> WorkoutComponent,
	private val profileComponentFactory: (ComponentContext, () -> Unit, () -> Unit) -> ProfileComponent,
	private val detailsProgressComponentFactory: (ComponentContext, () -> Unit) -> DetailsProgressComponent,
	private val listProgressComponentFactory: (ComponentContext, (Int) -> Unit, () -> Unit) -> ListProgressComponent,
) {

	fun main(
		componentContext: ComponentContext,
		onWorkout: (Int) -> Unit,
		onProfile: () -> Unit,
	): MainComponent =
		mainComponentFactory(componentContext, onWorkout, onProfile)

	fun workout(
		componentContext: ComponentContext,
		idExercise: Int
	): WorkoutComponent =
		workoutComponentFactory(componentContext, idExercise)

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
}