package app.magic.wilson.zach.com.magicappkotlin.models

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.Reader
import java.util.Date

/**
 * A Price model for each Card's various prices.
 */
data class Price(
        val high: Double,
        val mid: Double,
        val low: Double,
        val foil: Double,
        val date: Date,
        val name: String,
        val set: String,
        val _id: String
){



    class Deserializer : ResponseDeserializable<Price> {
        override fun deserialize(reader: Reader) = Gson().fromJson(reader, Price::class.java)
    }


    class ListDeserializer : ResponseDeserializable<List<Price>> {
        override fun deserialize(reader: Reader): List<Price> {
            val type = object : TypeToken<List<Price>>() {}.type
            return Gson().fromJson(reader, type)
        }
    }

}