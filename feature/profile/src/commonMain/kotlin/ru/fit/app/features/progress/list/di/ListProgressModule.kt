package ru.fit.app.features.progress.list.di

import com.arkivanov.decompose.ComponentContext
import org.koin.dsl.module
import ru.fit.app.features.progress.list.data.repository.ProgressExerciseRepositoryImpl
import ru.fit.app.features.progress.list.domain.repository.ProgressExerciseRepository
import ru.fit.app.features.progress.list.domain.usecase.GetProgressExerciseUseCase
import ru.fit.app.features.progress.list.presentation.ListProgressComponent

val ListProgressModule = module {
	factory<ProgressExerciseRepository> { ProgressExerciseRepositoryImpl() }

	factory { GetProgressExerciseUseCase(get()) }

	factory { (
				  componentContext: ComponentContext,
				  onNavigateDetailsProgress: (Int) -> Unit,
				  onBack: () -> Unit,
			  ) ->
		ListProgressComponent(
			componentContext = componentContext,
			getProgressExerciseUseCase = get(),
			openDetailsProgress = onNavigateDetailsProgress,
			back = onBack,
		)
	}
}