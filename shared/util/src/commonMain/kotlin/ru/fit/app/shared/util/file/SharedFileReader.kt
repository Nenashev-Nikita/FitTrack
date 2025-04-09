package ru.fit.app.shared.util.file

expect class SharedFileReader() {

	fun loadJsonFile(fileName: String): String?
}