package ru.fit.app.core.mvi

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.getOrElse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class BaseMviImpl<Action : ActionMvi, Effect : EffectMvi, Event : EventMvi, State : StateMvi>(
	tag: String,
	defaultState: State,
	scope: CoroutineScope,
	dispatcher: CoroutineDispatcher,
	reducer: (Effect, State) -> State = { _, state -> state },
	bootstrap: suspend () -> Unit = {},
	actor: suspend () -> Unit = {},
) : BaseMvi<Action, Effect, Event, State> {

	/*private val bufferStateFlow: MutableStateFlow<State> = MutableSharedFlow(
		extraBufferCapacity = 1,
		onBufferOverflow = BufferOverflow.DROP_OLDEST
	)*/

	private val eventChannel: Channel<Event> = Channel()
	private val effectChannel: Channel<Effect> = Channel()
	private val actionChannel: Channel<Action> = Channel()

	override fun push(event: Event) = eventChannel
		.trySend(event)
		.getOrElse { }

	override fun push(effect: Effect) = effectChannel
		.trySend(effect)
		.getOrElse { }

	override fun push(action: Action) = actionChannel
		.trySend(action)
		.getOrElse { }

	override val state: StateFlow<State>
		get() = TODO("Not yet implemented")

	override val events: Flow<Event> by lazy {
		eventChannel
			.receiveAsFlow()
			.onEach { }
			.flowOn(dispatcher)
	}

/*	override val state: StateFlow<State> by lazy {
		bufferStateFlow
			.onStart { }
			.onStart {
				scope.launch({
					actionChannel
						.receiveAsFlow()
						.onEach()
						.onEach { launchActor(it, actor) }
						.retryIfError()
					launchIn(scope: this)

					effectChannel
						.receiveAsFlow()
						.onEach()
						.map { reducer(it, stateFlow.value) }
						.onEach(bufferStateFlow::emit)
						.onEach()
						.retryIfError()
						.launchIn(scope: this)

					launchBootstrap(bootstrap)
				}
			}
			.distinctUntilChanged()
			.flowon(dispatcher)
			.retryIfError()
			.stateIn(scope, SharingStarted.Lazily, defaultState)
	}*/
}