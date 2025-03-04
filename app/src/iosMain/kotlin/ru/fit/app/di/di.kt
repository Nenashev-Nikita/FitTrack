package ru.fit.app.di

import org.koin.core.context.startKoin

fun initKoin() {
	startKoin {
		modules()
	}
}