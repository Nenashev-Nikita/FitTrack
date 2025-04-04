package ru.fit.app

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import ru.fit.app.features.main.ui.ScreenContent
import ru.fit.app.features.main.ui.ScreenExercise
import ru.fit.app.features.main.ui.ScreenProgram
import ru.fit.app.navigation.RootComponent

@Composable
fun App(root: RootComponent) {
	MaterialTheme {
		Column {
			Children(
				stack = root.childStack,
				animation = stackAnimation(slide())
			) { child ->
				when (val instance = child.instance) {
					is RootComponent.Child.Exercise -> ScreenExercise(instance.component)
					is RootComponent.Child.Main -> ScreenContent(instance.component)
					is RootComponent.Child.Program  -> ScreenProgram(instance.component)
				}
			}

			Row {
				Button(
					onClick = { root.onTabClicked(RootComponent.Config.Main) },
				) {

				}
				Button(
					onClick = { root.onTabClicked(RootComponent.Config.Program) },
				) {

				}
				Button(
					onClick = { root.onTabClicked(RootComponent.Config.Exercise) },
				) {

				}
			}
		}
	}
}