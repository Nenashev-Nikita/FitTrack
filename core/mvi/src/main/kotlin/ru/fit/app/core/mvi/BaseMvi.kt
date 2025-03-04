package ru.fit.app.core.mvi

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface BaseMvi<Action : ActionMvi, Effect : EffectMvi, Event : EventMvi, State : StateMvi> {

	val state: StateFlow<State>

	val events: Flow<Event>

	fun push(action: Action)

	fun push(effect: Effect)

	fun push(event: Event)
}