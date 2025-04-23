package ru.fit.app.features.exercise.di

import com.arkivanov.decompose.ComponentContext
import org.koin.dsl.module
import ru.fit.app.features.exercise.presentation.MainComponent
import ru.fit.app.features.exercise.presentation.MainNavigation

val MainModule = module {
	factory { (
				  componentContext: ComponentContext,
				  mainNavigation: MainNavigation,
			  ) ->
		MainComponent(
			componentContext = componentContext,
			mainNavigation = mainNavigation,
			getTrainingsUseCase = get(),
		)
	}
}