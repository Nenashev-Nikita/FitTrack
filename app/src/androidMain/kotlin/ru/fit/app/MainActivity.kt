package ru.fit.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.arkivanov.decompose.retainedComponent
import ru.fit.app.presentation.RootComponent

class MainActivity : ComponentActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		val root = retainedComponent {
			RootComponent(
				componentContext = it,
			)
		}

		setContent {
			App(root)
		}
	}
}