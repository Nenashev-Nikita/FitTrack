package ru.fit.app.features.main.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.fit.app.shared.training.domain.usecase.GetTrainingsUseCase

class MainViewModel(
	val getTrainingsUseCase: GetTrainingsUseCase,
) : ViewModel() {

	private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

	private val _screenState = MutableStateFlow<State>(State.Initial)
	val screenState: StateFlow<State> = _screenState.asStateFlow()

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
}