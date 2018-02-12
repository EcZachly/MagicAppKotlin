package app.magic.wilson.zach.com.magicappkotlin.api

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.Request

/**
 * The API Client to get card data from the server.
 */


fun getCards(color: String?, rarity: String?) : Request {
    // TODO("Make this more generic to enable other languages")
    return Fuel.get("/mtg/cards", listOf("language" to "en", "colors" to "red"))
}
