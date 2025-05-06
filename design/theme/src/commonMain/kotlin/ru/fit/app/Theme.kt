package ru.fit.app

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import ru.fit.app.fitnessclub.DarkColors
import ru.fit.app.fitnessclub.FitColors
import ru.fit.app.fitnessclub.LightColors
import ru.fit.app.fitnessclub.LocalFitColors

object FitTheme {

	val colors: FitColors
		@Composable
		get() = LocalFitColors.current
}

@Composable
fun FitTheme(
	darkTheme: Boolean = isSystemInDarkTheme(),
	content: @Composable () -> Unit
) {
	val colorScheme = if (darkTheme) DarkColors else LightColors

	CompositionLocalProvider(
		LocalFitColors provides colorScheme,
		content = content
	)
}