package id.fahrizal.spellchecker.domain

import id.fahrizal.spellchecker.data.CityManager
import timber.log.Timber
import java.util.Date
import javax.inject.Inject

class GetCity @Inject constructor(
    private val cityManager: CityManager
){

    operator fun invoke(text: String): String {
        val startTime = Date().time
        var nearest = Pair(99,"")
        for (city in cityManager.getCities()) {
            val different = SpellChecker.differentiate(text, city)

            if(different < 1){
                nearest = Pair(different, city)
                break
            }

            if(different < nearest.first){
                nearest = Pair(different, city)
            }
        }

        val endTime = Date().time - startTime
        Timber.d("Spell Checking Time: $endTime")
        return nearest.second
    }
}