package ru.fit.app.tabs

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable
import ru.fit.app.features.exercise.details.presentation.ExerciseComponent
import ru.fit.app.features.exercise.presentation.MainComponent
import ru.fit.app.features.exercise.presentation.ProgramComponent
import ru.fit.app.utils.Url
import ru.fit.app.utils.consumePathSegment
import ru.fit.app.utils.pathSegmentOf

class TabsComponent(
	componentContext: ComponentContext,
	deepLinkUrl: Url?,
	private val mainComponentFactory: () -> MainComponent,
	private val exerciseComponentFactory: () -> ExerciseComponent,
	private val programComponentFactory: () -> ProgramComponent,
) : ComponentContext by componentContext {

	private val navigation = StackNavigation<Config>()

	private val childStack = childStack(
		source = navigation,
		serializer = Config.serializer(),
		initialConfiguration = getInitialConfig(deepLinkUrl),
		handleBackButton = true,
		childFactory = ::createChild
	)

	val stack: Value<ChildStack<*, Child>> = childStack

	fun onMainTabClicked() {
		navigation.bringToFront(Config.Main)
	}

	fun onProgramTabClicked() {
		navigation.bringToFront(Config.Program)
	}

	fun onExerciseTabClicked() {
		navigation.bringToFront(Config.Exercise)
	}

	private fun createChild(config: Config, componentContext: ComponentContext): Child =
		when (config) {
			is Config.Main     ->
				Child.Main(mainComponentFactory())

			is Config.Program  ->
				Child.Program(programComponentFactory())

			is Config.Exercise ->
				Child.Exercise(exerciseComponentFactory())
		}

	private fun getInitialConfig(deepLinkUrl: Url?): Config {
		val (path) = deepLinkUrl?.consumePathSegment() ?: return Config.Main

		return when (path) {
			pathSegmentOf<Config.Program>()  -> Config.Program
			pathSegmentOf<Config.Exercise>() -> Config.Exercise
			else                             -> Config.Main
		}
	}

	sealed class Child {
		data class Main(val component: MainComponent) : Child()
		data class Program(val component: ProgramComponent) : Child()
		data class Exercise(val component: ExerciseComponent) : Child()
	}

	@Serializable
	private sealed interface Config {

		@Serializable
		data object Main : Config

		@Serializable
		data object Program : Config

		@Serializable
		data object Exercise : Config
	}
}