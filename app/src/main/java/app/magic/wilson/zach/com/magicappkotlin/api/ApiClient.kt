package app.magic.wilson.zach.com.magicappkotlin.api

import android.util.Log
import app.magic.wilson.zach.com.magicappkotlin.models.Card
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.fuel.core.Response
import com.github.kittinunf.result.Result
import java.sql.ResultSet

/**
 * Created by Zach on 2/10/18.
 */


fun getCards(color: String?, rarity: String?) : Request {
    return Fuel.get("/mtg/cards", listOf("language" to "en", "colors" to "red"))
}
