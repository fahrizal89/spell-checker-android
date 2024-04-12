package id.fahrizal.spellchecker.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
inline fun DebounceTextField(
    modifier: Modifier,
    crossinline onTextChanged: (String) -> Unit,
    label: String = "",
    hint: String = "",
    singleLine: Boolean = true
) {
    var text by remember { mutableStateOf("") }
    val prevDebouncedText = remember { mutableStateOf("") }

    text.useDebounce {
        if (prevDebouncedText.value != text) {
            onTextChanged(it)
        }
    }

    val keyboardOptions: KeyboardOptions = if (singleLine) {
        KeyboardOptions(
            imeAction = ImeAction.Done,
            capitalization = KeyboardCapitalization.Sentences,
        )
    } else {
        KeyboardOptions(imeAction = ImeAction.Default)
    }

    Text(
        text = label,
        modifier = Modifier.padding(8.dp, 0.dp, 8.dp, 0.dp),
        color = MaterialTheme.colors.primary,
    )
    Column {
        OutlinedTextField(
            value = text,
            onValueChange = {
                text = it
            },
            placeholder = { Text(hint) },
            singleLine = singleLine,
            keyboardOptions = keyboardOptions,
            modifier = modifier,
        )
    }
}

@Composable
inline fun <T> T.useDebounce(
    delayMillis: Long = 400L,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    crossinline onChange: (T) -> Unit,
): T {
    val state by rememberUpdatedState(this)

    DisposableEffect(state) {
        val job = coroutineScope.launch {
            delay(delayMillis)
            onChange(state)
        }
        onDispose {
            job.cancel()
        }
    }
    return state
}