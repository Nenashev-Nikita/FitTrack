package ru.fit.app.design.component.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun AppBar(
	title: String,
	leftIcon: ImageVector? = null,
	onLeftButtonClick: (() -> Unit)? = null,
	rightIconMap: Map<ImageVector, () -> Unit>? = null,
) {
	TopAppBar(
		title = {
			Text(text = title)
		},
		modifier = Modifier
			.fillMaxWidth()
			.shadow(4.dp),
		navigationIcon = {
			leftIcon?.let { icon ->
				IconButton(
					onClick = { onLeftButtonClick?.invoke() },
				) {
					Icon(
						imageVector = icon,
						contentDescription = null
					)
				}
			}
		},
		actions = {
			rightIconMap?.let { map ->
				for ((rightIcon, onButtonClick) in map) {
					IconButton(
						onClick = { onButtonClick.invoke() },
					) {
						Icon(
							imageVector = rightIcon,
							contentDescription = null
						)
					}
				}
			}
		},
	)
}