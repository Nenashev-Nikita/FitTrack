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
import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable
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
				val exercises = listOf(
					ExerciseProgress(1, 1, LocalDate(2025, 5, 1), 100f),
					ExerciseProgress(2, 1, kotlinx.datetime.LocalDate(2025, 5, 2), 105f),
					ExerciseProgress(3, 1, kotlinx.datetime.LocalDate(2025, 5, 3), 110f),
					ExerciseProgress(4, 1, kotlinx.datetime.LocalDate(2025, 5, 4), 108f),
					ExerciseProgress(5, 1, kotlinx.datetime.LocalDate(2025, 5, 5), 112f)
				)
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

@Serializable
data class ExerciseProgress(
	val id: Int,
	val exerciseId: Int,
	val date: LocalDate,
	val oneRepMax: Float
)