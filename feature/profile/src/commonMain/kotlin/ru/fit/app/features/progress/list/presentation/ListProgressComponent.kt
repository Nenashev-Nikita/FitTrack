package ru.fit.app.features.progress.list.presentation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.lifecycle.subscribe
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.fit.app.features.progress.list.domain.usecase.GetProgressExerciseUseCase

class ListProgressComponent(
	componentContext: ComponentContext,
	private val getProgressExerciseUseCase: GetProgressExerciseUseCase,
	private val openDetailsProgress: (Int) -> Unit,
	private val back: () -> Unit,
) : ComponentContext by componentContext {

	private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

	private val _screenState = MutableStateFlow<State>(State.Initial)
	val screenState: StateFlow<State> = _screenState.asStateFlow()

	init {
		lifecycle.subscribe(
			onDestroy = { coroutineScope.cancel() }
		)
	}

	fun loadContent() {
		coroutineScope.launch {
			_screenState.value = State.Loading
			try {
				val exercises = getProgressExerciseUseCase()
				_screenState.value = State.Content(
					exercises = exercises,
				)
			} catch (e: Exception) {
				_screenState.value = State.Error
			}
		}
	}

	fun navigationDetails(id: Int) {
		openDetailsProgress(id)
	}

	fun navigationBack() {
		back()
	}
}