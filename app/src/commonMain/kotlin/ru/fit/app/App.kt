package ru.fit.app

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import ru.fit.app.features.main.ui.Content
import ru.fit.app.navigation.RootComponent

@Composable
fun App(root: RootComponent) {
	MaterialTheme {
		val childStack by root.childStack.subscribeAsState()

		Children(
			stack = childStack,
			animation = stackAnimation(slide())
		) { child ->
			when (val instance = child.instance) {
				is RootComponent.Child.ScreenMain -> Content(instance.component)
			}
		}
	}
}