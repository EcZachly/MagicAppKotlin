package app.magic.wilson.zach.com.magicappkotlin

import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.gson.responseObject
import kotlinx.android.synthetic.main.activity_main.clickForCards
import kotlinx.android.synthetic.main.activity_main.cardList
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import app.magic.wilson.zach.com.magicappkotlin.adapters.CardAdapter
import app.magic.wilson.zach.com.magicappkotlin.api.getCards
import app.magic.wilson.zach.com.magicappkotlin.models.Card

class MainActivity : AppCompatActivity() {

    val rarities = mutableListOf("mythic", "rare", "uncommon", "common")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        FuelManager.instance.apply {
            basePath = "http://magicapp.herokuapp.com"
        }

        val linearLayoutManager = LinearLayoutManager(this)

        cardList.layoutManager = linearLayoutManager
        val activity = this
        clickForCards.setOnClickListener {
            getCards(this,"red", "mythic").responseObject<List<Card>>{
                _, _, result ->
                val adapter = CardAdapter(activity, result.get())
                cardList.adapter = adapter
            }

        }
    }
}
