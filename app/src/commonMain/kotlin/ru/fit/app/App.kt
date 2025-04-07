package ru.fit.app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.plus
import com.arkivanov.decompose.extensions.compose.stack.animation.predictiveback.predictiveBackAnimation
import com.arkivanov.decompose.extensions.compose.stack.animation.scale
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import ru.fit.app.features.main.ui.ScreenExercise
import ru.fit.app.features.main.ui.ScreenMain
import ru.fit.app.features.main.ui.ScreenProgram
import ru.fit.app.features.workout.ui.ScreenWorkout
import ru.fit.app.navigation.RootComponent

@Composable
fun App(component: RootComponent, modifier: Modifier = Modifier) {
	MaterialTheme {
		Children(
			component = component,
			modifier = modifier,
		)
	}
}

@OptIn(ExperimentalDecomposeApi::class)
@Composable
private fun Children(component: RootComponent, modifier: Modifier = Modifier) {
	Children(
		stack = component.stack,
		modifier = modifier,
		animation = predictiveBackAnimation(
			backHandler = component.backHandler,
			fallbackAnimation = stackAnimation(fade() + scale()),
			onBack = component::onBackClicked,
		),
	) {
		Surface(modifier = Modifier.fillMaxSize()) {
			when (val child = it.instance) {
				is RootComponent.Child.Exercise -> ScreenExercise(child.component)

				is RootComponent.Child.Main     -> ScreenMain(child.component)

				is RootComponent.Child.Program  -> ScreenProgram(child.component)

				is RootComponent.Child.Workout  -> ScreenWorkout(child.component)
			}
		}
	}
}