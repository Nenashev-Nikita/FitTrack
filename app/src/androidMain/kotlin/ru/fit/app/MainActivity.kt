package ru.fit.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.arkivanov.decompose.retainedComponent
import org.koin.android.ext.android.get
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import ru.fit.app.di.FactoryComponentModule
import ru.fit.app.di.RootModule
import ru.fit.app.di.navigationModule
import ru.fit.app.features.details.di.DetailsModule
import ru.fit.app.features.exercise.details.di.ExerciseModule
import ru.fit.app.features.exercise.di.MainModule
import ru.fit.app.features.exercise.di.ProgramModule
import ru.fit.app.features.profile.di.profileModule
import ru.fit.app.features.progress.details.di.DetailsProgressModule
import ru.fit.app.features.progress.list.di.ListProgressModule
import ru.fit.app.features.selection.di.SelectionModule
import ru.fit.app.features.shared.di.FeatureWorkoutComponentModule
import ru.fit.app.features.workout.di.WorkoutModule
import ru.fit.app.presentation.RootComponent
import ru.fit.app.shared.exercise.di.ExerciseListModule
import ru.fit.app.shared.profile.di.UserProfileModule
import ru.fit.app.shared.training.di.TrainingModule

class MainActivity : ComponentActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		startKoin {
			androidLogger(Level.DEBUG)
			modules(
				RootModule,
				FactoryComponentModule,
				navigationModule,
			)
			//Shared
			modules(
				TrainingModule,
				UserProfileModule,
				ExerciseListModule,
			)
			//Feature
			modules(
				MainModule,
				ProgramModule,
				ExerciseModule,
				profileModule,
				ListProgressModule,
				DetailsProgressModule,
				FeatureWorkoutComponentModule,
				WorkoutModule,
				SelectionModule,
				DetailsModule,
			)
		}

		val root = retainedComponent {
			RootComponent(
				componentContext = it,
				factoryComponent = get(),
			)
		}

		setContent {
			App(root)
		}
	}
}