package app.magic.wilson.zach.com.magicappkotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import app.magic.wilson.zach.com.magicappkotlin.adapters.DetailCardPagerAdapter
import app.magic.wilson.zach.com.magicappkotlin.adapters.PrintThumbnailAdapter
import app.magic.wilson.zach.com.magicappkotlin.constants.Keys
import app.magic.wilson.zach.com.magicappkotlin.models.Card
import com.google.gson.Gson
import com.nshmura.recyclertablayout.RecyclerTabLayout
import kotlinx.android.synthetic.main.activity_detail_card_view.*

/**
 * An activity to view the details of a particular Card.
 */

class DetailCardViewActivity : AppCompatActivity() {

    private lateinit var cardViewPager: ViewPager
    private lateinit var cardThumbsTabs: RecyclerTabLayout
    private lateinit var pagerAdapter: DetailCardPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_card_view)

        if (intent.hasExtra(Keys.CARD_KEY)){

            val json = intent.getStringExtra(Keys.CARD_KEY)
            val card = Gson().fromJson<Card>(json, Card::class.java)

            detail_card_name.text = card.name

            setCardPrintAdapter(card)
        }
    }

    private fun setCardPrintAdapter(card: Card){
        cardViewPager = findViewById<ViewPager>(R.id.detail_card_vp)

        pagerAdapter = DetailCardPagerAdapter(supportFragmentManager, card)
        cardViewPager.adapter = pagerAdapter

        cardThumbsTabs = findViewById(R.id.detail_card_tabs)

        cardThumbsTabs.setUpWithAdapter(PrintThumbnailAdapter(this, cardViewPager, card))
        cardThumbsTabs.setCurrentItem(1, false)

        cardViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageScrollStateChanged(state: Int) {
                if (state == ViewPager.SCROLL_STATE_IDLE) {
                    val lastPosition = cardViewPager.adapter.count - 1
                    val currentPosition = cardViewPager.currentItem

                    if (currentPosition == lastPosition) {
                        // When the user scrolls past the "last" content page, move to the "first"
                        cardThumbsTabs.setCurrentItem(1, false)
                    } else if (currentPosition == 0) {
                        // When the user scrolls before the "first" content page, move to the "last"
                        cardThumbsTabs.setCurrentItem(lastPosition - 1, false)
                    }

                }
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }
            override fun onPageSelected(position: Int) {

            }

        })
    }
}
