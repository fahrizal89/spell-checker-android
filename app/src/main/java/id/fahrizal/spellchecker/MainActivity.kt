package id.fahrizal.spellchecker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import id.fahrizal.spellchecker.ui.SpellCheckerScreen
import id.fahrizal.spellchecker.ui.SpellCheckerViewModel

class MainActivity : ComponentActivity() {

    private val spellCheckerViewModel : SpellCheckerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SpellCheckerScreen(spellCheckerViewModel)
        }
    }
}