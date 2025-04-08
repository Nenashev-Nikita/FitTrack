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
import ru.fit.app.features.profile.di.profileModule
import ru.fit.app.features.profile.presentation.ProfileComponent
import ru.fit.app.features.progress.details.di.DetailsProgressModule
import ru.fit.app.features.progress.details.presentation.DetailsProgressComponent
import ru.fit.app.features.progress.list.di.ListProgressModule
import ru.fit.app.features.progress.list.presentation.ListProgressComponent
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
				profileModule,
				ListProgressModule,
				DetailsProgressModule,
			)
		}

		val root = retainedComponent {
			RootComponent(
				componentContext = it,
				mainComponentFactory = { context, onWorkoutSelected, onProfileSelected ->
					get<MainComponent>(parameters = {
						parametersOf(context, onWorkoutSelected, onProfileSelected)
					})
				},
				workoutComponentFactory = { context, id ->
					get<WorkoutComponent>(parameters = {
						parametersOf(context, id)
					})
				},
				profileComponentFactory = { componentContext, onMain, onList ->
					get<ProfileComponent>(
						parameters = {
							parametersOf(componentContext, onMain, onList)
						}
					)
				},
				detailsProgressComponentFactory = { context, onBack ->
					get<DetailsProgressComponent>(parameters = {
						parametersOf(context, onBack)
					})
				},
				listProgressComponentFactory = { context, onMain, onDetailsProgress ->
					get<ListProgressComponent>(parameters = {
						parametersOf(context, onMain, onDetailsProgress)
					})
				}
			)
		}

		setContent {
			App(root)
		}
	}
}