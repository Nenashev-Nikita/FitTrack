package ru.fit.app.features.progress.details.di

import com.arkivanov.decompose.ComponentContext
import org.koin.dsl.module
import ru.fit.app.features.progress.details.presentation.DetailsProgressComponent

val DetailsProgressModule = module {

	factory { (
				  componentContext: ComponentContext,
				  onBack: () -> Unit,
			  ) ->
		DetailsProgressComponent(
			componentContext = componentContext,
			getProgressExerciseUseCase = get(),
			back = onBack,
		)
	}
}