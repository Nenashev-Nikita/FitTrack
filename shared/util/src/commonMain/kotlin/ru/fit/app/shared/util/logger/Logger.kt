package ru.fit.app.shared.util.logger

expect object Logger {

	fun e(tag: String, message: String, throwable: Throwable? = null)
	fun d(tag: String, message: String)
	fun i(tag: String, message: String)
}