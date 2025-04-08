package ru.fit.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.arkivanov.decompose.retainedComponent
import org.koin.android.ext.android.get
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.parameter.parametersOf
import ru.fit.app.di.RootModule
import ru.fit.app.features.main.di.ExerciseModule
import ru.fit.app.features.main.di.MainModule
import ru.fit.app.features.main.di.ProgramModule
import ru.fit.app.features.main.presentation.MainComponent
import ru.fit.app.features.workout.di.WorkoutModule
import ru.fit.app.features.workout.presentation.WorkoutComponent
import ru.fit.app.presentation.RootComponent
import ru.fit.app.shared.training.di.TrainingModule

class MainActivity : ComponentActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		startKoin {
			androidLogger(Level.DEBUG)
			modules(
				RootModule,
			)
			//Shared
			modules(
				TrainingModule,
			)
			//Feature
			modules(
				MainModule,
				ProgramModule,
				ExerciseModule,
				WorkoutModule,
			)
		}

		val root = retainedComponent {
			RootComponent(
				componentContext = it,
				mainComponentFactory = { context, onWorkoutSelected ->
					get<MainComponent>(parameters = {
						parametersOf(context, onWorkoutSelected)
					})
				},
				workoutComponentFactory = { context, id ->
					get<WorkoutComponent>(parameters = {
						parametersOf(context, id)
					})
				},
			)
		}

		setContent {
			App(root)
		}
	}
}