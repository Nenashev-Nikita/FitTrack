package ru.fit.app.features.progress.details.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.fit.app.features.progress.details.presentation.DetailsProgressComponent

@Composable
fun ScreenDetailsProgress(component: DetailsProgressComponent) {

	Column(
		modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
	) {
		Text(text = "ScreenDetailsProgress")
	}
}