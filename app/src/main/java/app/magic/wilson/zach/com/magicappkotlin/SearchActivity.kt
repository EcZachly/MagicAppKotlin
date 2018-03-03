package app.magic.wilson.zach.com.magicappkotlin

import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.gson.responseObject
import kotlinx.android.synthetic.main.activity_search.clickForCards
import kotlinx.android.synthetic.main.activity_search.cardList
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import app.magic.wilson.zach.com.magicappkotlin.adapters.CardAdapter
import app.magic.wilson.zach.com.magicappkotlin.api.getCards
import app.magic.wilson.zach.com.magicappkotlin.models.Card

/**
 * An activity to search for cards.
 */

class SearchActivity : AppCompatActivity() {

    val rarities = mutableListOf("mythic", "rare", "uncommon", "common")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        FuelManager.instance.apply {
            basePath = resources.getString(R.string.api_base_url)
        }

        val linearLayoutManager = LinearLayoutManager(this)

        cardList.layoutManager = linearLayoutManager
        val activity = this
        clickForCards.setOnClickListener {
            getCards(this,"red", "mythic").responseObject<List<Card>>{
                _, _, result ->
                val adapter = CardAdapter(activity, result.get(), { card : Card -> cardClicked(card) })
                cardList.adapter = adapter
            }

        }
    }

    private fun cardClicked(card : Card) {
        Toast.makeText(this, "Clicked: ${card.name}", Toast.LENGTH_LONG).show()
    }
}
