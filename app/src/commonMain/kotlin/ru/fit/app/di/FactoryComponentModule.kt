package ru.fit.app.di

import org.koin.core.parameter.parametersOf
import org.koin.dsl.module
import ru.fit.app.features.main.presentation.MainComponent
import ru.fit.app.features.profile.presentation.ProfileComponent
import ru.fit.app.features.progress.details.presentation.DetailsProgressComponent
import ru.fit.app.features.progress.list.presentation.ListProgressComponent
import ru.fit.app.features.workout.presentation.WorkoutComponent
import ru.fit.app.presentation.FactoryComponent

val FactoryComponentModule = module {

	factory {
		FactoryComponent(
			mainComponentFactory = { context, onWorkoutSelected, onProfileSelected ->
				get<MainComponent>(parameters = {
					parametersOf(context, onWorkoutSelected, onProfileSelected)
				})
			},
			workoutComponentFactory = { context, id ->
				get<WorkoutComponent>(parameters = {
					parametersOf(context, id)
				})
			},
			profileComponentFactory = { componentContext, onMain, onList ->
				get<ProfileComponent>(
					parameters = {
						parametersOf(componentContext, onMain, onList)
					}
				)
			},
			detailsProgressComponentFactory = { context, onBack ->
				get<DetailsProgressComponent>(parameters = {
					parametersOf(context, onBack)
				})
			},
			listProgressComponentFactory = { context, onMain, onDetailsProgress ->
				get<ListProgressComponent>(parameters = {
					parametersOf(context, onMain, onDetailsProgress)
				})
			}
		)
	}
}