package ru.fit.app.core.mvi

interface StateMvi {
	val log get() = this.toString()
}