package app.magic.wilson.zach.com.magicappkotlin.adapters

import android.support.v4.app.FragmentManager
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentStatePagerAdapter
import app.magic.wilson.zach.com.magicappkotlin.fragments.CardViewFragment
import app.magic.wilson.zach.com.magicappkotlin.models.Card

/**
 * An Adapter to display the CardViewFragments for the DetailCardViewActivity.
 */

// A value to inflate the size of the PagerAdapter to enable infinite scrolling
private const val MAX_VALUE = 3

class DetailCardPagerAdapter(fragmentManager: FragmentManager, private val cards: List<Card>) : FragmentStatePagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return CardViewFragment.newInstance(cards[position % cards.size])
    }

    override fun getCount(): Int {
        // Inflated with extra space to create the effect of infinite scrolling
        return cards.size * MAX_VALUE
    }

    // Images are displayed instead of a title
    override fun getPageTitle(position: Int): CharSequence {
        return ""
    }

}