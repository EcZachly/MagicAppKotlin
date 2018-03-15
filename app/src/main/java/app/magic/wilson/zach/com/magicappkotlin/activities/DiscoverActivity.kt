package app.magic.wilson.zach.com.magicappkotlin.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.preference.PreferenceManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import app.magic.wilson.zach.com.magicappkotlin.R
import app.magic.wilson.zach.com.magicappkotlin.adapters.CardAdapter
import app.magic.wilson.zach.com.magicappkotlin.adapters.DiscoverGroupAdapter
import app.magic.wilson.zach.com.magicappkotlin.api.getCards
import app.magic.wilson.zach.com.magicappkotlin.constants.Keys
import app.magic.wilson.zach.com.magicappkotlin.models.Card
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.gson.responseObject
import kotlinx.android.synthetic.main.activity_discover.*
import com.google.gson.Gson
import java.util.*


/**
 * An activity to discover new cards.
 */
class DiscoverActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_discover)

        // The first thing we want to do is detect the language and save the information to SharedPreferences
        // so we can filter card images by language
        detectLanguage()

//        TODO("Get new cards from the server.  Ensures most updated information")
        populateCards(discover_new_cards)

//        TODO("Get recommended cards from the server")
        populateCards(discover_recommended_cards)

        val colorsGroup = resources.getStringArray(R.array.card_colors_array)
        populateRecyclerViews(discover_colors_cards, colorsGroup)

        val rarityGroup = resources.getStringArray(R.array.card_rarities_array)
        populateRecyclerViews(discover_rarity_cards, rarityGroup)

        val typesGroup = resources.getStringArray(R.array.card_types_array)
        populateRecyclerViews(discover_types_cards, typesGroup)

//        TODO("Get subtypes from the server.  Ensures most updated information")
        val subtypesGroup = Array<String>(1, {"Subtypes"})
        populateRecyclerViews(discover_subtypes_cards, subtypesGroup)

//        TODO("Get sets from the server.  Ensures most updated information")
        val setsGroup = Array<String>(1, {"Sets"})
        populateRecyclerViews(discover_sets_cards, setsGroup)

    }

    // Helper method to populate each category of card groupings
    private fun populateRecyclerViews(recyclerView: RecyclerView, array: Array<String>){
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL

        recyclerView.layoutManager = linearLayoutManager
        val adapter = DiscoverGroupAdapter(this, array)
        recyclerView.adapter = adapter
    }

    // Helper method to populate the categories that will display cards
//    TODO("Set parameters for this method that will filter cards accordingly")
    private fun populateCards(recyclerView: RecyclerView){
//        TODO("Update the call to the server")
        FuelManager.instance.apply {
            basePath = resources.getString(R.string.api_base_url)
        }

        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL

        recyclerView.layoutManager = linearLayoutManager
        val activity = this
        getCards(this,null, null).responseObject<List<Card>>{
            _, _, result ->
            val adapter = CardAdapter(activity, result.get(), { card : Card -> cardClicked(card) })
            recyclerView.adapter = adapter
        }
    }

    private fun detectLanguage(){
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)

        // If the user has not set their language preferences, detect what language preferences to use
        if (sharedPreferences.getString(getString(R.string.settings_language_key), "").isEmpty()) {
            // deviceLanguage is the language code
            val deviceLanguage = Locale.getDefault().language

            // validLanguages is a predetermined list of languages the app has data for and can support
            val validLanguages = resources.getStringArray(R.array.card_languages_array)

            // if the language is supported, then filter the card results to that language.
            // Otherwise default to English
            val filterLanguage = if (validLanguages.contains(deviceLanguage)) deviceLanguage else getString(R.string.language_en)

            // Save the language to sharedPreferences so we can use it to access the correct card images
            with (sharedPreferences.edit()) {
                putString(getString(R.string.settings_language_key), filterLanguage)
                commit()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.discover, menu)
        return super.onCreateOptionsMenu(menu)
    }

    // TODO Redesign action bar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
//            TODO("Implement search action")
            R.id.action_search -> {}
//            TODO("Implement view saved searches action")
            R.id.action_view_starred -> {}
//            TODO("Remove this action from this menu.  Placed here for testing/building purposes only.")
            R.id.action_settings -> {
                val intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun cardClicked(card : Card) {
        val gson = Gson()
        val json = gson.toJson(card)

        val intent = Intent(this, DetailCardViewActivity::class.java)
        intent.putExtra(Keys.CARD_KEY, json)
        startActivity(intent)
    }
}