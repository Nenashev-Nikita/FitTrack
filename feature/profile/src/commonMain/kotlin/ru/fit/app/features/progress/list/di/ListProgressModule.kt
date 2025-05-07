package ru.fit.app.features.progress.list.di

import com.arkivanov.decompose.ComponentContext
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.factory
import org.kodein.di.instance
import ru.fit.app.features.progress.list.data.repository.ProgressExerciseRepositoryImpl
import ru.fit.app.features.progress.list.domain.repository.ProgressExerciseRepository
import ru.fit.app.features.progress.list.domain.usecase.GetProgressExerciseUseCase
import ru.fit.app.features.progress.list.presentation.ListProgressComponent

data class ListProgressArgs(
	val componentContext: ComponentContext,
	val onNavigateDetailsProgress: (Int) -> Unit,
	val onBack: () -> Unit
)

val ListProgressModule = DI.Module("ListProgressModule") {
	bind<ProgressExerciseRepository>() with factory { ProgressExerciseRepositoryImpl() }

	bind<GetProgressExerciseUseCase>() with factory {
		GetProgressExerciseUseCase(instance())
	}

	bind<ListProgressComponent>() with factory { args: ListProgressArgs ->
		ListProgressComponent(
			componentContext = args.componentContext,
			getProgressExerciseUseCase = instance(),
			openDetailsProgress = args.onNavigateDetailsProgress,
			back = args.onBack
		)
	}
}