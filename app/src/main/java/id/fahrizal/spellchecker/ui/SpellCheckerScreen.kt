package id.fahrizal.spellchecker.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import id.fahrizal.spellchecker.R

@Composable
fun SpellCheckerScreen(
    viewModel: SpellCheckerViewModel = viewModel()
) {

    Column {
        DebounceTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp, 10.dp, 8.dp, 2.dp),
            label = stringResource(id = R.string.search_food),
            onTextChanged = { text ->
                viewModel.search(text)
            },
            singleLine = true
        )

        Text(
            modifier = Modifier.padding(start = 8.dp, top = 8.dp),
            text = stringResource(id = R.string.food) +" :"
        )

        when (val state = viewModel.uiState.collectAsState().value) {
            is SpellCheckerViewModel.SpellCheckerUiState.Loaded -> {
                Text(
                    modifier = Modifier.padding(start = 8.dp, top = 8.dp),
                    fontSize = MaterialTheme.typography.h5.fontSize,
                    text = state.city
                )
            }

            is SpellCheckerViewModel.SpellCheckerUiState.Loading -> {
                Text(
                    modifier = Modifier.padding(start = 8.dp),
                    fontSize = MaterialTheme.typography.h5.fontSize,
                    text = "..."
                )
            }
            is SpellCheckerViewModel.SpellCheckerUiState.Error -> {
                Text(
                    modifier = Modifier.padding(start = 8.dp),
                    color = Color.Red,
                    text = "- error -"
                )
            }
        }
    }
}