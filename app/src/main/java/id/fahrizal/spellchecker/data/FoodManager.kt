package id.fahrizal.spellchecker.data

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import id.fahrizal.spellchecker.util.JsonUtil
import javax.inject.Inject

class FoodManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val foods : List<String> = JsonUtil.load(context, "foods.json", List::class.java) as List<String>

    fun getFoods() = foods
}