package ru.fit.app.shared.training.domain.entity

data class Training(
	val name: String,
	val date: Long, // TODO подумать какая будет дата
	val exercise: Exercise,
)