package ru.fit.app.fitnessclub

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val lightBGPrimary = Color(0xFFFFFFFF)
val lightBGSecondary = Color(0xFFF2F2F2)
val lightBGTertiary = Color(0xFFEEEEEE)
val lightFondPrimary = Color(0xFF3E3E3E)
val lightFondSecondary = Color(0xFF878787)
val lightFondTertiary = Color(0xFFABABAB)
val lightFondInvert = Color(0xFFFFFFFF)
val lightPermanentPrimary = Color(0xFFFF5252)
val lightPermanentPrimaryLight = Color(0xFFFF7A7A)
val lightIndicatorError = Color(0xFFB7C1C1)

val darkBGPrimary = Color(0xFF212121)
val darkBGSecondary = Color(0xFF1E1E1E)
val darkBGTertiary = Color(0xFF343434)
val darkFondPrimary = Color(0xFFF6F6F6)
val darkFondSecondary = Color(0xFF878A96)
val darkFondTertiary = Color(0xFF717174)
val darkFondInvert = Color(0xFF3E3E3E)
val darkPermanentPrimary = Color(0xFFFF5252)
val darkPermanentPrimaryLight = Color(0xFFFF7A7A)
val darkIndicatorError = Color(0xFFAC4343)

@Immutable
data class FitColors(
	val bGPrimary: Color,
	val bGSecondary: Color,
	val bGTertiary: Color,
	val fondPrimary: Color,
	val fondSecondary: Color,
	val fondTertiary: Color,
	val fondInvert: Color,
	val permanentPrimary: Color,
	val permanentPrimaryLight: Color,
	val indicatorError: Color,
)

val LocalFitColors = staticCompositionLocalOf {
	FitColors(
		bGPrimary = Color.Unspecified,
		bGSecondary = Color.Unspecified,
		bGTertiary = Color.Unspecified,
		fondPrimary = Color.Unspecified,
		fondSecondary = Color.Unspecified,
		fondTertiary = Color.Unspecified,
		fondInvert = Color.Unspecified,
		permanentPrimary = Color.Unspecified,
		permanentPrimaryLight = Color.Unspecified,
		indicatorError = Color.Unspecified
	)
}

val LightColors = FitColors(
	bGPrimary = lightBGPrimary,
	bGSecondary = lightBGSecondary,
	bGTertiary = lightBGTertiary,
	fondPrimary = lightFondPrimary,
	fondSecondary = lightFondSecondary,
	fondTertiary = lightFondTertiary,
	fondInvert = lightFondInvert,
	permanentPrimary = lightPermanentPrimary,
	permanentPrimaryLight = lightPermanentPrimaryLight,
	indicatorError = lightIndicatorError
)

val DarkColors = FitColors(
	bGPrimary = darkBGPrimary,
	bGSecondary = darkBGSecondary,
	bGTertiary = darkBGTertiary,
	fondPrimary = darkFondPrimary,
	fondSecondary = darkFondSecondary,
	fondTertiary = darkFondTertiary,
	fondInvert = darkFondInvert,
	permanentPrimary = darkPermanentPrimary,
	permanentPrimaryLight = darkPermanentPrimaryLight,
	indicatorError = darkIndicatorError
)