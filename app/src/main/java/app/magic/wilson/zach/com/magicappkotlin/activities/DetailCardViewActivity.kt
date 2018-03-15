package app.magic.wilson.zach.com.magicappkotlin.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import app.magic.wilson.zach.com.magicappkotlin.R
import app.magic.wilson.zach.com.magicappkotlin.adapters.DetailCardPagerAdapter
import app.magic.wilson.zach.com.magicappkotlin.constants.Keys
import app.magic.wilson.zach.com.magicappkotlin.interfaces.PagerLoop
import app.magic.wilson.zach.com.magicappkotlin.models.Card
import com.google.gson.Gson
import com.nshmura.recyclertablayout.RecyclerTabLayout
import kotlinx.android.synthetic.main.activity_detail_card_view.*

/**
 * An activity to view the details of a particular Card.
 */

class DetailCardViewActivity : AppCompatActivity(), PagerLoop {

    private lateinit var cardViewPager: ViewPager
    private lateinit var cardThumbsTabs: RecyclerTabLayout
    private lateinit var pagerAdapter: DetailCardPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_card_view)

        supportActionBar?.hide()

        if (intent.hasExtra(Keys.CARD_KEY)){

            val json = intent.getStringExtra(Keys.CARD_KEY)
            val card = Gson().fromJson<Card>(json, Card::class.java)

            detail_card_name.text = card.name

            cardViewPager = findViewById<ViewPager>(R.id.detail_card_vp)
            pagerAdapter = DetailCardPagerAdapter(this, supportFragmentManager, card)
            cardThumbsTabs = findViewById(R.id.detail_card_tabs)

            initializeLoopingAdapter(this, cardViewPager, pagerAdapter, cardThumbsTabs, card)
        }
    }
}
