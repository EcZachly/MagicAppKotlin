package app.magic.wilson.zach.com.magicappkotlin.api

import android.app.Activity
import android.content.Context
import android.support.v7.preference.PreferenceManager
import app.magic.wilson.zach.com.magicappkotlin.R
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.Request
import java.util.Locale

/**
 * The API Client to get card data from the server.
 */

fun getCards(activity: Activity, color: String?, rarity: String?) : Request {
    val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity)
    val filterLanguage = sharedPreferences.getString(activity.getString(R.string.settings_language_key), activity.getString(R.string.language_en))

    return Fuel.get("/mtg/cards", listOf("language" to filterLanguage, "colors" to color, "rarity" to rarity))
}
