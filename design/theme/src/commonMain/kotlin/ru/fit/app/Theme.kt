// Theme.kt
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val PrimaryColor = Color(0xFFFC4445)
private val SecondaryColor = Color(0xFF3F51B5)
private val TertiaryColor = Color(0xFF4CAF50)
private val ErrorColor = Color(0xFFB00020)

private val LightColors = lightColorScheme(
	primary = PrimaryColor,
	onPrimary = Color.White,
	primaryContainer = PrimaryColor.copy(alpha = 0.1f),
	onPrimaryContainer = PrimaryColor.copy(alpha = 0.9f),
	inversePrimary = PrimaryColor.copy(alpha = 0.8f),
	secondary = SecondaryColor,
	onSecondary = Color.White,
	secondaryContainer = SecondaryColor.copy(alpha = 0.1f),
	onSecondaryContainer = SecondaryColor.copy(alpha = 0.9f),
	tertiary = TertiaryColor,
	onTertiary = Color.White,
	tertiaryContainer = TertiaryColor.copy(alpha = 0.1f),
	onTertiaryContainer = TertiaryColor.copy(alpha = 0.9f),
	background = Color(0xFFF5F5F5),
	onBackground = Color(0xFF212121),
	surface = Color.White,
	onSurface = Color(0xFF212121),
	surfaceVariant = Color(0xFFEEEEEE),
	onSurfaceVariant = Color(0xFF757575),
	surfaceTint = PrimaryColor,
	inverseSurface = Color(0xFF2D2D2D),
	inverseOnSurface = Color(0xFFF5F5F5),
	error = ErrorColor,
	onError = Color.White,
	errorContainer = ErrorColor.copy(alpha = 0.1f),
	onErrorContainer = ErrorColor.copy(alpha = 0.9f),
	outline = Color(0xFFBDBDBD),
	outlineVariant = Color(0xFFE0E0E0),
	scrim = Color(0x99000000),
	surfaceBright = Color(0xFFFAFAFA),
	surfaceContainer = Color(0xFFF0F0F0),
	surfaceContainerHigh = Color(0xFFEAEAEA),
	surfaceContainerHighest = Color(0xFFE0E0E0),
	surfaceContainerLow = Color(0xFFF5F5F5),
	surfaceContainerLowest = Color.White,
	surfaceDim = Color(0xFFD8D8D8)
)

private val DarkColors = darkColorScheme(
	primary = PrimaryColor.copy(alpha = 0.8f),
	onPrimary = Color.White,
	primaryContainer = PrimaryColor.copy(alpha = 0.3f),
	onPrimaryContainer = Color.White.copy(alpha = 0.9f),
	inversePrimary = PrimaryColor.copy(alpha = 0.6f),
	secondary = SecondaryColor.copy(alpha = 0.8f),
	onSecondary = Color.White,
	secondaryContainer = SecondaryColor.copy(alpha = 0.3f),
	onSecondaryContainer = Color.White.copy(alpha = 0.9f),
	tertiary = TertiaryColor.copy(alpha = 0.8f),
	onTertiary = Color.White,
	tertiaryContainer = TertiaryColor.copy(alpha = 0.3f),
	onTertiaryContainer = Color.White.copy(alpha = 0.9f),
	background = Color(0xFF121212),
	onBackground = Color.White.copy(alpha = 0.87f),
	surface = Color(0xFF1E1E1E),
	onSurface = Color.White.copy(alpha = 0.87f),
	surfaceVariant = Color(0xFF2D2D2D),
	onSurfaceVariant = Color.White.copy(alpha = 0.6f),
	surfaceTint = PrimaryColor.copy(alpha = 0.8f),
	inverseSurface = Color(0xFFE0E0E0),
	inverseOnSurface = Color(0xFF2D2D2D),
	error = ErrorColor.copy(alpha = 0.8f),
	onError = Color.White,
	errorContainer = ErrorColor.copy(alpha = 0.3f),
	onErrorContainer = ErrorColor.copy(alpha = 0.9f),
	outline = Color(0xFF666666),
	outlineVariant = Color(0xFF444444),
	scrim = Color(0x99000000),
	surfaceBright = Color(0xFF383838),
	surfaceContainer = Color(0xFF272727),
	surfaceContainerHigh = Color(0xFF333333),
	surfaceContainerHighest = Color(0xFF404040),
	surfaceContainerLow = Color(0xFF1E1E1E),
	surfaceContainerLowest = Color(0xFF0A0A0A),
	surfaceDim = Color(0xFF121212)
)

@Composable
fun FitTheme(
	darkTheme: Boolean = isSystemInDarkTheme(),
	content: @Composable () -> Unit,
) {
	val colorScheme = if (darkTheme) DarkColors else LightColors

	MaterialTheme(
		colorScheme = colorScheme,
		content = content
	)
}