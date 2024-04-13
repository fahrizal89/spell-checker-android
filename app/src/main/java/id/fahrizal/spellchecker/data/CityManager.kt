package id.fahrizal.spellchecker.data

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import id.fahrizal.spellchecker.util.JsonUtil
import javax.inject.Inject

class CityManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val cities : List<String> = JsonUtil.load(context, "city.json", List::class.java) as List<String>

    fun getCities() = cities
}