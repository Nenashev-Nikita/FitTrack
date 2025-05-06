package ru.fit.app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.predictiveback.predictiveBackAnimation
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import ru.fit.app.features.profile.ui.ProfileScreen
import ru.fit.app.features.progress.details.ui.ScreenDetailsProgress
import ru.fit.app.features.progress.list.ui.ScreenListProgress
import ru.fit.app.features.shared.ui.ScreenFeatureWorkout
import ru.fit.app.presentation.RootComponent
import ru.fit.app.tabs.TabsContent

@Composable
fun App(component: RootComponent, modifier: Modifier = Modifier) {
	FitTheme {
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
			fallbackAnimation = stackAnimation(fade()),
			onBack = component::onBackClicked,
		),
	) {
		Surface(modifier = Modifier.fillMaxSize()) {
			when (val child = it.instance) {
				is RootComponent.Child.Workout -> ScreenFeatureWorkout(child.component)

				is RootComponent.Child.TabsChild       -> TabsContent(child.component)

				is RootComponent.Child.Profile -> ProfileScreen(child.component)

				is RootComponent.Child.DetailsProgress -> ScreenDetailsProgress(child.component)

				is RootComponent.Child.ListProgress    -> ScreenListProgress(child.component)
			}
		}
	}
}