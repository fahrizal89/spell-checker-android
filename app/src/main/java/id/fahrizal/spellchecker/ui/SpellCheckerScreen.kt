package id.fahrizal.spellchecker.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import id.fahrizal.spellchecker.R

@Composable
fun SpellCheckerScreen() {

    Column {
        DebounceTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp, 2.dp, 8.dp, 2.dp),
            label = stringResource(id = R.string.search_city),
            onTextChanged = { text ->
                //viewModel.search(text)
            },
            singleLine = true
        )

        Text(
            modifier = Modifier.padding(start = 8.dp),
            text = stringResource(id = R.string.city) +" :"
        )


    }
}