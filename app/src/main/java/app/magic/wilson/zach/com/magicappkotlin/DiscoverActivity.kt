package app.magic.wilson.zach.com.magicappkotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import app.magic.wilson.zach.com.magicappkotlin.adapters.DiscoverAdapter
import kotlinx.android.synthetic.main.activity_discover.*

/**
 * An activity to discover new cards.
 */

class DiscoverActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_discover)

//        TODO("Get new cards from the server.  Ensures most updated information")
        val newGroup = Array<String>(1, {"New"})
        populateRecyclerViews(discover_new_cards, newGroup)

//        TODO("Get recommended cards from the server")
        val recommendedGroup = Array<String>(1, {"Recommended"})
        populateRecyclerViews(discover_recommended_cards, recommendedGroup)

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

    private fun populateRecyclerViews(recyclerView: RecyclerView, array: Array<String>){

        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL

        recyclerView.layoutManager = linearLayoutManager
        val adapter = DiscoverAdapter(this, array)
        recyclerView.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.discover, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
//            TODO("Implement search action")
            R.id.action_search -> {}
//            TODO("Implement view saved searches action")
            R.id.action_view_starred -> {}
        }
        return super.onOptionsItemSelected(item)
    }
}