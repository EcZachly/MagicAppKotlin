package app.magic.wilson.zach.com.magicappkotlin.interfaces

import android.app.Activity
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager
import app.magic.wilson.zach.com.magicappkotlin.adapters.DetailPrintThumbAdapter
import app.magic.wilson.zach.com.magicappkotlin.models.Card
import com.nshmura.recyclertablayout.RecyclerTabLayout

/**
 * An interface to define the methods a Pager that has an infinite loop cycle should implement.
 */

interface PagerLoop{
    fun initializeLoopingAdapter (activity: Activity, viewPager: ViewPager, pagerAdapter: FragmentStatePagerAdapter, adapterTabs: RecyclerTabLayout, card: Card){

        viewPager.adapter = pagerAdapter

        adapterTabs.setUpWithAdapter(DetailPrintThumbAdapter(activity, viewPager, card))
        adapterTabs.setCurrentItem(1, false)

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageScrollStateChanged(state: Int) {
                if (state == ViewPager.SCROLL_STATE_IDLE) {
                    val lastPosition = viewPager.adapter.count - 1
                    val currentPosition = viewPager.currentItem

                    if (currentPosition == lastPosition) {
                        // When the user scrolls past the "last" content page, move to the "first"
                        adapterTabs.setCurrentItem(1, false)
                    } else if (currentPosition == 0) {
                        // When the user scrolls before the "first" content page, move to the "last"
                        adapterTabs.setCurrentItem(lastPosition - 1, false)
                    }

                }
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                // Required override method
            }
            override fun onPageSelected(position: Int) {
                // Required override method
            }

        })
    }
}