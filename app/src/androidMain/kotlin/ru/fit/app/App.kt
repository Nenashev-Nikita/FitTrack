package ru.fit.app

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.fit.app.features.main.di.MainModule
import ru.fit.app.shared.training.di.TrainingModule

class App : Application() {

	override fun onCreate() {
		super.onCreate()
		startKoin {
			androidContext(this@App)
			modules(
				TrainingModule,
				MainModule,
			)
		}
	}
}