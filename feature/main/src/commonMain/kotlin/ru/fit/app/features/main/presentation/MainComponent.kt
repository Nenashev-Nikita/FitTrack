package ru.fit.app.features.main.presentation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.lifecycle.subscribe
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.fit.app.core.presentation.BaseComponent
import ru.fit.app.shared.training.domain.usecase.GetTrainingsUseCase

class MainComponent(
	componentContext: ComponentContext,
	val getTrainingsUseCase: GetTrainingsUseCase,
) : BaseComponent(componentContext) {

	private val _screenState = MutableStateFlow<State>(State.Initial)
	val screenState: StateFlow<State> = _screenState.asStateFlow()

	init {
		lifecycle.subscribe(
			onDestroy = { scope.cancel() }
		)
	}

	fun loadContent() {
		launch {
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
}