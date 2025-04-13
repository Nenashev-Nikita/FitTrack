package ru.fit.app.features.workout.data.network

import kotlinx.coroutines.delay
import ru.fit.app.core.network.client.Ktor
import ru.fit.app.shared.training.data.module.TrainingModule
import ru.fit.app.shared.util.file.SharedFileReader

class WorkoutApi {

	suspend fun get(id: Int): TrainingModule =
		parseData()

	private suspend fun parseData(): TrainingModule {
		val json = Ktor.json
		delay(500)
		val sharedFileReader = SharedFileReader()

		val jsonString = sharedFileReader.loadJsonFile("training.json") ?: ""
		return json.decodeFromString(jsonString)
	}
}