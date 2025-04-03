package ru.fit.app.features.main.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import ru.fit.app.features.main.presentation.MainViewModel

val MainModule = module {

	viewModel { MainViewModel(get()) }
}