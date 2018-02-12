package app.magic.wilson.zach.com.magicappkotlin.models

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.Reader

/**
 * A Ruling model.
 */


data class Ruling(
        val date: String,
        val text: String
){

    class Deserializer : ResponseDeserializable<Ruling> {
        override fun deserialize(reader: Reader) = Gson().fromJson(reader, Ruling::class.java)
    }

    class ListDeserializer : ResponseDeserializable<List<Ruling>> {
        override fun deserialize(reader: Reader): List<Ruling> {
            val type = object : TypeToken<List<Ruling>>() {}.type
            return Gson().fromJson(reader, type)
        }
    }
}