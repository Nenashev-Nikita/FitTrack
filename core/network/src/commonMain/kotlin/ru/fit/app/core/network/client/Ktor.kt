package ru.fit.app.core.network.client

import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.utils.io.ByteReadChannel
import kotlinx.serialization.json.Json
import ru.fit.app.shared.util.file.SharedFileReader

object Ktor {

	val json = Json {
		ignoreUnknownKeys = true
		explicitNulls = false
		coerceInputValues = true
	}
	private val responseHeaders = headersOf("Content-Type" to listOf(ContentType.Application.Json.toString()))
	val sharedFileReader = SharedFileReader()

	val client = HttpClient(MockEngine) {
		install(ContentNegotiation) {
			json
		}
		engine {
			addHandler { request ->
				when (request.url.encodedPath) {
					"/api/gateway/training" -> {
						respond(
							content = ByteReadChannel(getContent("trainings.json")),
							status = HttpStatusCode.OK,
							headers = responseHeaders
						)
					}

					else                    -> error("Unhandled request: ${request.url}")
				}
			}
		}
	}

	private fun getContent(fileName: String): String =
		sharedFileReader.loadJsonFile(fileName) ?: ""
}