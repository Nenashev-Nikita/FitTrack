package ru.fit.app.features.progress.details.presentation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.lifecycle.subscribe
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import ru.fit.app.features.progress.list.domain.usecase.GetProgressExerciseUseCase

class DetailsProgressComponent(
	componentContext: ComponentContext,
	private val getProgressExerciseUseCase: GetProgressExerciseUseCase,
	private val back: () -> Unit,
) : ComponentContext by componentContext {

	private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

	init {
		lifecycle.subscribe(
			onDestroy = { coroutineScope.cancel() }
		)
	}

	fun loadContent() {
		coroutineScope.launch {

		}
	}

	fun navigationBack() {
		back()
	}
}