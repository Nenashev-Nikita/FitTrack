package ru.fit.app

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val lightPrimary = Color(0xFFFFFFFF)
val lightTextPrimary = Color(0xFF141C24)

val darkPrimary = Color(0xFFFFFFFF)
val darkTextPrimary = Color(0xFF141C24)

@Immutable
data class FitColors(
	val primary: Color,
	val textPrimary: Color,
)

val LocalFitColors = staticCompositionLocalOf {
	FitColors(
		primary = Color.Unspecified,
		textPrimary = Color.Unspecified,
	)
}

val LightColors = FitColors(
	primary = lightPrimary,
	textPrimary = lightTextPrimary,
)

val DarkColors = FitColors(
	primary = darkPrimary,
	textPrimary = darkTextPrimary,
)