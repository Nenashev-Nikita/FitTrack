package ru.fit.app

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import com.arkivanov.decompose.router.stack.active
import ru.fit.app.features.main.ui.ScreenContent
import ru.fit.app.features.main.ui.ScreenExercise
import ru.fit.app.features.main.ui.ScreenProgram
import ru.fit.app.navigation.RootComponent
import ru.fit.app.navigation.RootComponent.Config

@Composable
fun App(root: RootComponent) {
	MaterialTheme {
		Scaffold(
			bottomBar = {
				NavigationBar {
					NavigationBarItem(
						selected = root.childStack.active.configuration == Config.Main,
						onClick = { root.onTabClicked(Config.Main) },
						icon = { Icon(imageVector = Icons.Default.Home, contentDescription = "Main") },
						label = { Text("Main") }
					)
					NavigationBarItem(
						selected = root.childStack.active.configuration == Config.Exercise,
						onClick = { root.onTabClicked(Config.Exercise) },
						icon = { Icon(imageVector = Icons.Default.Search, contentDescription = "Exercise") },
						label = { Text("Exercise") }
					)
					NavigationBarItem(
						selected = root.childStack.active.configuration == Config.Program,
						onClick = { root.onTabClicked(Config.Program) },
						icon = { Icon(imageVector = Icons.Default.FitnessCenter, contentDescription = "Program") },
						label = { Text("Program") }
					)
				}
			}
		) { paddingValues ->
			Children(
				modifier = Modifier.padding(paddingValues),
				stack = root.childStack,
				animation = stackAnimation(slide())
			) { child ->
				when (val instance = child.instance) {
					
					is RootComponent.Child.Exercise -> ScreenExercise(instance.component)

					is RootComponent.Child.Main -> ScreenContent(instance.component)

					is RootComponent.Child.Program  -> ScreenProgram(instance.component)
				}
			}
		}
	}
}