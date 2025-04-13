package ru.fit.app.shared.exercise.data.network

import ru.fit.app.core.network.client.Ktor
import ru.fit.app.shared.exercise.domain.entity.Exercise

class ExerciseApi {

	private val json = Ktor.json
	private val sharedFileReader = Ktor.sharedFileReader

	suspend fun getAll(): List<Exercise> =
		json.decodeFromString(sharedFileReader.loadJsonFile("exercises.json") ?: "")

	suspend fun get(id: Int): Exercise =
		json.decodeFromString(sharedFileReader.loadJsonFile("exercise.json") ?: "")
}