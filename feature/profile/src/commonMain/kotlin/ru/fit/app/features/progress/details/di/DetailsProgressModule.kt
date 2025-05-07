package ru.fit.app.features.progress.details.di

import com.arkivanov.decompose.ComponentContext
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.factory
import org.kodein.di.instance
import ru.fit.app.features.progress.details.presentation.DetailsProgressComponent

data class DetailsProgressArgs(
	val componentContext: ComponentContext,
	val back: () -> Unit,
)

val DetailsProgressModule = DI.Module("DetailsProgressModule") {
	bind<DetailsProgressComponent>() with factory { args: DetailsProgressArgs ->

		DetailsProgressComponent(
			componentContext = args.componentContext,
			getProgressExerciseUseCase = instance(),
			back = args.back,
		)
	}
}