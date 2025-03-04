package ru.fit.app.core.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.request
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import io.ktor.client.request.get


internal fun createKtorClient(): HttpClient = HttpClient {

	install(HttpTimeout) {
		requestTimeoutMillis = 15_000
	}

	install(ContentNegotiation) {
		json(Json {
			ignoreUnknownKeys = true
			prettyPrint = true

		})

	}

	install(Logging) {
		level = LogLevel.ALL
	}

}

/*
val networkModule get() = module { this: Module

	single<HttpClient> { createKtorClient() }

}*/

suspend inline fun <reified T> HttpClient.fetch(
	block: HttpRequestBuilder.() -> Unit
): Result<T> = try {
	val response = request(block)
	if (response.status == HttpStatusCode.OK)
		Result.success(response.body())
	else
		Result.failure(Throwable("${response.status}: ${response.bodyAsText()}"))
} catch (e: Exception) {
	Result.failure(e)
}

suspend inline fun <reified T> HttpClient.fetchForGet(
	url: String,
): Result<T> = try {
	val response = get(url)
	if (response.status == HttpStatusCode.OK)
		Result.success(response.body())
	else
		Result.failure(Throwable("${response.status}: ${response.bodyAsText()}"))
} catch (e: Exception){
	Result.failure(e)
}