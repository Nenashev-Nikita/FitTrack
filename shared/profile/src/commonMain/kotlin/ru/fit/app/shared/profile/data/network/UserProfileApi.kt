package ru.fit.app.shared.profile.data.network

import kotlinx.coroutines.delay
import kotlinx.serialization.json.Json
import ru.fit.app.shared.profile.data.module.UserProfileModule
import ru.fit.app.shared.util.file.SharedFileReader

class UserProfileApi {

	suspend fun get(): UserProfileModule =
		parseData()

	private suspend fun parseData(): UserProfileModule {
		delay(500)
		val sharedFileReader = SharedFileReader()

		val jsonString = sharedFileReader.loadJsonFile("profile.json") ?: ""
		return Json.decodeFromString(jsonString)
	}
}