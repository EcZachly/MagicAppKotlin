package app.magic.wilson.zach.com.magicappkotlin.models

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.Reader

/**
 * An Edition model for the types of Card editions.
 */
data class Edition(
        val set: String,
        val artist: String,
        val number: String,
        val layout: String,
        val multiverse_id: Int,
        val rarity: String,
        val price: Price
){


    class Deserializer : ResponseDeserializable<Edition> {
        override fun deserialize(reader: Reader) = Gson().fromJson(reader, Edition::class.java)
    }


    class ListDeserializer : ResponseDeserializable<List<Edition>> {
        override fun deserialize(reader: Reader): List<Edition> {
            val type = object : TypeToken<List<Edition>>() {}.type
            return Gson().fromJson(reader, type)
        }
    }
}