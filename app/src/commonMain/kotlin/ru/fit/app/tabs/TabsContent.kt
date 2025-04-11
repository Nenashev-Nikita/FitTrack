package ru.fit.app.tabs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import ru.fit.app.features.main.ui.ScreenExercise
import ru.fit.app.features.main.ui.ScreenMain
import ru.fit.app.features.main.ui.ScreenProgram

@Composable
internal fun TabsContent(
	component: TabsComponent,
	modifier: Modifier = Modifier,
) {
	Column(modifier = modifier) {
		Children(component = component, modifier = Modifier.weight(1F).consumeWindowInsets(WindowInsets.navigationBars))
		BottomBar(component = component, modifier = Modifier.fillMaxWidth())
	}
}

@Composable
private fun Children(component: TabsComponent, modifier: Modifier = Modifier) {
	Children(
		stack = component.stack,
		modifier = modifier,
		animation = stackAnimation(fade()),
	) {
		when (val child = it.instance) {
			is TabsComponent.Child.Exercise -> ScreenExercise(child.component)

			is TabsComponent.Child.Main     -> ScreenMain(child.component)

			is TabsComponent.Child.Program  -> ScreenProgram(child.component)
		}
	}
}

@Composable
private fun BottomBar(component: TabsComponent, modifier: Modifier = Modifier) {
	val stack by component.stack.subscribeAsState()
	val activeComponent = stack.active.instance

	BottomNavigation(
		modifier = modifier
			.fillMaxWidth()
			.navigationBarsPadding(),
		elevation = 0.dp,
	) {
		NavigationBarItem(
			selected = activeComponent is TabsComponent.Child.Main,
			onClick = component::onMainTabClicked,
			icon = { androidx.compose.material3.Icon(imageVector = Icons.Default.Home, contentDescription = "Main") },
			label = { Text("Main") }
		)
		NavigationBarItem(
			selected = activeComponent is TabsComponent.Child.Exercise,
			onClick = component::onExerciseTabClicked,
			icon = { androidx.compose.material3.Icon(imageVector = Icons.Default.Search, contentDescription = "Exercise") },
			label = { Text("Exercise") }
		)
		NavigationBarItem(
			selected = activeComponent is TabsComponent.Child.Program,
			onClick = component::onProgramTabClicked,
			icon = { androidx.compose.material3.Icon(imageVector = Icons.Default.FitnessCenter, contentDescription = "Program") },
			label = { Text("Program") }
		)
	}
}