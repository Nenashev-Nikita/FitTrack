package ru.fit.app.features.exercise.presentation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.lifecycle.doOnDestroy
import com.arkivanov.essenty.lifecycle.subscribe
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.fit.app.shared.training.domain.usecase.GetTrainingsUseCase

class MainComponent(
	componentContext: ComponentContext,
	private val getTrainingsUseCase: GetTrainingsUseCase,
	private val onWorkoutSelected: (Int) -> Unit,
	private val onProfileSelected: () -> Unit,
) : ComponentContext by componentContext {

	private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

	private val _screenState = MutableStateFlow<State>(State.Initial)
	val screenState: StateFlow<State> = _screenState.asStateFlow()

	init {
		lifecycle.subscribe(
			onDestroy = { coroutineScope.cancel() }
		)
		lifecycle.doOnDestroy {
			coroutineScope.cancel()
		}
	}

	fun loadContent() {
		coroutineScope.launch {
			_screenState.value = State.Loading
			try {
				val trainings = getTrainingsUseCase()
				_screenState.value = State.Content(
					trainings = trainings,
				)
			} catch (e: Exception) {
				_screenState.value = State.Error
			}
		}
	}

	fun navigationWorkout(id: Int) {
		onWorkoutSelected(id)
	}

	fun navigationProfile() {
		onProfileSelected()
	}
}