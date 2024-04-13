package id.fahrizal.spellchecker.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.fahrizal.spellchecker.domain.GetCity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SpellCheckerViewModel @Inject constructor(
    private val getCity: GetCity
) : ViewModel() {

    private val _uiState = MutableStateFlow<SpellCheckerUiState>(SpellCheckerUiState.Loaded())
    val uiState: StateFlow<SpellCheckerUiState> = _uiState

    fun search(text: String) {
        if(text.length < 4) return

        viewModelScope.launch(Dispatchers.IO) {
            val result = getCity.invoke(text)
            _uiState.value = SpellCheckerUiState.Loaded(result)
        }
    }

    sealed class SpellCheckerUiState {
        object Loading : SpellCheckerUiState()
        class Loaded(val city: String="") : SpellCheckerUiState()
        class Error(val msg: String) : SpellCheckerUiState()
    }
}
