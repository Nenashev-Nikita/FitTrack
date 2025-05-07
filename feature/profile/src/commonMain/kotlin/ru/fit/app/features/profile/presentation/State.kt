package ru.fit.app.features.profile.presentation

import ru.fit.app.shared.profile.domain.entity.UserProfile

sealed interface State {

	data object Initial : State

	data object Loading : State

	data object Error : State

	data class Content(
		val user: UserProfile,
	) : State
}