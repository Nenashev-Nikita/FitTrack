package ru.fit.app.features.main.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.koin.compose.viewmodel.koinViewModel
import ru.fit.app.features.main.presentation.MainViewModel
import ru.fit.app.features.main.presentation.State

@Composable
fun Content() {
	val viewModel = koinViewModel<MainViewModel>()
	val screenState by viewModel.screenState.collectAsState()
	val lazyListState = rememberLazyListState()

	MaterialTheme {
		Column(
			modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
		) {
			when (val state = screenState) {
				State.Initial    -> {
					LaunchedEffect(Unit) {
						viewModel.loadContent()
					}
				}

				State.Error      -> Text("Error")

				State.Loading    -> CircularProgressIndicator()

				is State.Content -> {
					LazyColumn(
						Modifier.fillMaxWidth(),
						horizontalAlignment = Alignment.CenterHorizontally,
						state = lazyListState,
					) {
						items(state.trainings) { training ->
							Text(text = training.name)
						}
					}
				}
			}
		}
	}
}