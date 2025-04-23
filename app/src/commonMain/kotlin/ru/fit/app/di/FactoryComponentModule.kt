package ru.fit.app.di

import org.koin.core.parameter.parametersOf
import org.koin.dsl.module
import ru.fit.app.features.exercise.details.presentation.ExerciseComponent
import ru.fit.app.features.exercise.presentation.MainComponent
import ru.fit.app.features.exercise.presentation.ProgramComponent
import ru.fit.app.features.profile.presentation.ProfileComponent
import ru.fit.app.features.progress.details.presentation.DetailsProgressComponent
import ru.fit.app.features.progress.list.presentation.ListProgressComponent
import ru.fit.app.features.shared.presentation.FeatureWorkoutComponent
import ru.fit.app.presentation.FactoryComponent

val FactoryComponentModule = module {

	factory {
		FactoryComponent(
			mainComponentFactory = { context, mainNavigation ->
				get<MainComponent>(parameters = {
					parametersOf(context, mainNavigation)
				})
			},
			featureWorkoutComponent = { context, id ->
				get<FeatureWorkoutComponent>(parameters = {
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
			},
			exerciseComponentFactory = { context ->
				get<ExerciseComponent>(
					parameters = {
						parametersOf(context)
					}
				)
			},
			programComponentFactory = { context ->
				get<ProgramComponent>(
					parameters = {
						parametersOf(context)
					}
				)
			},
		)
	}
}