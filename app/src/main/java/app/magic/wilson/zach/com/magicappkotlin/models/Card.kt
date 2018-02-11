package app.magic.wilson.zach.com.magicappkotlin.models
import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.Reader

/**
 * Created by Zach on 2/10/18.
 */

data class Card(
        val name: String = "",
        val store_url: String = "",
        val imageURLs: Map<String, List<String>>?,
        val versions: Map<String, Int>,
        val rulings: List<Ruling>,
        val set: List<String>,
        val editions: List<Edition>
) {
    class Deserializer : ResponseDeserializable<Card> {
        override fun deserialize(reader: Reader) = Gson().fromJson(reader, Card::class.java)
    }


    class ListDeserializer : ResponseDeserializable<List<Card>> {
        override fun deserialize(reader: Reader): List<Card> {
            val type = object : TypeToken<List<Card>>() {}.type
            return Gson().fromJson(reader, type)
        }
    }
}