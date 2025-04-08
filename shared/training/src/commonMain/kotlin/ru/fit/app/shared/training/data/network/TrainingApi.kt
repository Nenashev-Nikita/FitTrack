package ru.fit.app.shared.training.data.network

import kotlinx.coroutines.delay
import kotlinx.serialization.json.Json
import ru.fit.app.shared.training.data.module.TrainingModule
import ru.fit.app.shared.util.file.SharedFileReader

class TrainingApi {

	/*suspend fun getAll(): List<TrainingModule> {
		return Ktor.client.get("/api/gateway/training").body()
	}*/

	suspend fun getAll(): List<TrainingModule> =
		parseData()

	private suspend fun parseData(): List<TrainingModule> {
		delay(500)
		val sharedFileReader = SharedFileReader()

		val jsonString = sharedFileReader.loadJsonFile("trainings.json") ?: ""
		return Json.decodeFromString(jsonString)
	}
}