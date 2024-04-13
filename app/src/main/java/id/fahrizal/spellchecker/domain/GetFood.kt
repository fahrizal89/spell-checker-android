package id.fahrizal.spellchecker.domain

import id.fahrizal.spellchecker.data.FoodManager
import javax.inject.Inject

class GetFood @Inject constructor(
    private val foodManager: FoodManager
){

    operator fun invoke(text: String): String {
        var nearest = Pair(99,"")
        foodManager.getFoods().forEach { food->
            val different = SpellChecker.differentiate(text, food)

            if(different <3) return food

            if(different < nearest.first){
                nearest = Pair(different, food)
            }
        }
        return nearest.second
    }
}