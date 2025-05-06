package ru.fit.app.design.component.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import ru.fit.app.FitTheme

data class AppBarAction(
	val icon: ImageVector? = null,
	val text: String? = null,
	val onClick: () -> Unit
)

@Composable
fun AppBar(
	title: String? = null,
	leftAction: AppBarAction? = null,
	rightActions: List<AppBarAction>? = null,
	modifier: Modifier = Modifier,
) {

	Row(
		modifier = Modifier
			.fillMaxWidth()
			.height(64.dp)
			.background(Color.Transparent)
			.padding(horizontal = 8.dp, vertical = 8.dp),
		verticalAlignment = Alignment.CenterVertically,
		horizontalArrangement = Arrangement.SpaceBetween
	) {
		leftAction?.let {
			SmallFloatingActionButton(
				onClick = leftAction.onClick,
				containerColor = FitTheme.colors.bGTertiary,
				contentColor = FitTheme.colors.fondSecondary,
			) {
				leftAction.icon?.let {
					Icon(
						imageVector = it,
						contentDescription = null
					)
				}
			}
		}

		title?.let {
			Text(
				text = title,
				style = MaterialTheme.typography.titleMedium,
				color = FitTheme.colors.fondPrimary,
				modifier = Modifier.weight(1f),
				textAlign = androidx.compose.ui.text.style.TextAlign.Center
			)
		}

		rightActions?.let {
			Row(
				horizontalArrangement = Arrangement.spacedBy(8.dp),
				verticalAlignment = Alignment.CenterVertically
			) {
				rightActions.forEach { action ->
					when {
						action.icon != null -> {
							SmallFloatingActionButton(
								onClick = action.onClick,
								containerColor = FitTheme.colors.bGTertiary,
								contentColor = FitTheme.colors.fondSecondary,
							) {
								Icon(
									imageVector = action.icon,
									contentDescription = null
								)
							}
						}

						action.text != null -> {
							TextButton(onClick = action.onClick) {
								Text(
									text = action.text,
									style = MaterialTheme.typography.labelLarge
								)
							}
						}
					}
				}
			}
		} ?: Box(modifier.size(56.dp))
	}
}

@Composable
fun AppBarExample() {
	AppBar(
		title = "My App",
		leftAction = AppBarAction(
			icon = androidx.compose.material.icons.Icons.Default.Menu,
			onClick = { /* Handle menu click */ }
		),
		rightActions = listOf(
			AppBarAction(
				icon = androidx.compose.material.icons.Icons.Default.Search,
				onClick = { /* Handle search click */ }
			),
			AppBarAction(
				text = "Profile",
				onClick = { /* Handle profile click */ }
			)
		)
	)
}