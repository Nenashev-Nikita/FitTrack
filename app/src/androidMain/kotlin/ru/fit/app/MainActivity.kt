package ru.fit.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.retainedComponent
import org.koin.android.ext.android.get
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import ru.fit.app.di.NavigationModule
import ru.fit.app.features.main.di.ExerciseModule
import ru.fit.app.features.main.di.MainModule
import ru.fit.app.features.main.di.ProgramModule
import ru.fit.app.features.main.presentation.ExerciseComponent
import ru.fit.app.features.main.presentation.MainComponent
import ru.fit.app.features.main.presentation.ProgramComponent
import ru.fit.app.features.workout.di.WorkoutModule
import ru.fit.app.navigation.RootComponent
import ru.fit.app.shared.training.di.TrainingModule

@OptIn(ExperimentalDecomposeApi::class)
class MainActivity : ComponentActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		startKoin {
			androidLogger(Level.DEBUG)
			modules(
				NavigationModule,
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
				mainComponentFactory = { context ->
					MainComponent(
						context,
						getTrainingsUseCase = get(),
					)
				},
				programComponentFactory = { context ->
					ProgramComponent(
						context,
					)
				},
				exerciseComponentFactory = { context ->
					ExerciseComponent(
						context,
					)
				},
			)
		}

		setContent {
			App(root)
		}
	}
}