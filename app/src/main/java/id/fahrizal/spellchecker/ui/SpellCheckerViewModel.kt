package id.fahrizal.spellchecker.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SpellCheckerViewModel @Inject constructor(
) : ViewModel() {

    private val _uiState = MutableStateFlow<SpellCheckerUiState>(SpellCheckerUiState.Loaded())
    val uiState: StateFlow<SpellCheckerUiState> = _uiState

    fun search(text: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value = SpellCheckerUiState.Loaded(text)
        }
    }

    sealed class SpellCheckerUiState {
        object Loading : SpellCheckerUiState()
        class Loaded(val city: String="") : SpellCheckerUiState()
        class Error(val msg: String) : SpellCheckerUiState()
    }
}
