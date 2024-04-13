package id.fahrizal.spellchecker.domain

import id.fahrizal.spellchecker.data.CityManager
import javax.inject.Inject

class GetCity @Inject constructor(
    private val cityManager: CityManager
){

    operator fun invoke(text: String): String {
        var nearest = Pair(99,"")
        cityManager.getCities().forEach { city->
            val different = SpellChecker.differentiate(text, city)

            if(different <3) return city

            if(different < nearest.first){
                nearest = Pair(different, city)
            }
        }
        return nearest.second
    }
}