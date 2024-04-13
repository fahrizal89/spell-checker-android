package id.fahrizal.spellchecker.util

import android.content.Context
import android.content.res.AssetManager
import com.google.gson.Gson

object JsonUtil {

    fun <T>load(context:Context, path:String, classOfT: Class<T>) : T{
        return Gson().fromJson(context.assets.readAssetsFile(path), classOfT)
    }

    private fun AssetManager.readAssetsFile(fileName : String): String =
        open(fileName).bufferedReader().use{it.readText()}
}
