package ru.fit.app.features.exercise.di

import com.arkivanov.decompose.ComponentContext
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.factory
import org.kodein.di.instance
import ru.fit.app.features.exercise.presentation.MainComponent
import ru.fit.app.features.exercise.presentation.MainNavigation

data class MainComponentArgs(
	val componentContext: ComponentContext,
	val mainNavigation: MainNavigation
)

val MainModule = DI.Module("MainModule") {

	bind<MainComponent>() with factory { args: MainComponentArgs ->
		MainComponent(
			componentContext = args.componentContext,
			mainNavigation = args.mainNavigation,
			getTrainingsUseCase = instance()
		)
	}
}