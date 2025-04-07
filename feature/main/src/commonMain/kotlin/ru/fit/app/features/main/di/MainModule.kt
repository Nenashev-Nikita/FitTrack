package ru.fit.app.features.main.di

import com.arkivanov.decompose.ComponentContext
import org.koin.dsl.module
import ru.fit.app.features.main.presentation.MainComponent

val MainModule = module {
	factory { (componentContext: ComponentContext, onWorkoutSelected: (Int) -> Unit) ->
		MainComponent(
			componentContext = componentContext,
			getTrainingsUseCase = get(),
			onWorkoutSelected = onWorkoutSelected
		)
	}
}