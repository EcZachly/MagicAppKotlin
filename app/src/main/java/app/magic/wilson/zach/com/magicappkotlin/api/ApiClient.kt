package app.magic.wilson.zach.com.magicappkotlin.api

import android.content.Context
import app.magic.wilson.zach.com.magicappkotlin.R
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.Request
import java.util.Locale

/**
 * The API Client to get card data from the server.
 */

fun getCards(context: Context, color: String?, rarity: String?) : Request {
    // deviceLanguage is the language code
    val deviceLanguage = Locale.getDefault().language

    // validLanguages is a predetermined list of languages the app has data for and can support
    val validLanguages = context.resources.getStringArray(R.array.card_languages_array)

    // if the language is supported, then filter the card results to that language.
    // Otherwise default to English
    if (validLanguages.contains(deviceLanguage)) {
        return Fuel.get("/mtg/cards", listOf("language" to deviceLanguage,
                "colors" to color, "rarity" to rarity))
    }

    return Fuel.get("/mtg/cards", listOf("language" to "en",
            "colors" to color, "rarity" to rarity))
}
