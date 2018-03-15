package app.magic.wilson.zach.com.magicappkotlin.adapters

import android.support.v4.app.FragmentManager
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentStatePagerAdapter
import app.magic.wilson.zach.com.magicappkotlin.fragments.DetailCardPrintFragment
import app.magic.wilson.zach.com.magicappkotlin.models.Card

/**
 * An Adapter to display the CardViewFragments for the DetailCardViewActivity.
 */

class DetailCardPagerAdapter(fragmentManager: FragmentManager, private val card: Card) : FragmentStatePagerAdapter(fragmentManager) {

    private val mImagesList = card.imageURLs.orEmpty()["en"]

    override fun getItem(position: Int): Fragment {
        return DetailCardPrintFragment.newInstance(card, position)
    }

    // Add extra Fragment on either side of real data to loop scrolling
    override fun getCount(): Int {
        return mImagesList.orEmpty().size + 2
    }

    // Images are displayed instead of a title
    override fun getPageTitle(position: Int): CharSequence {
        return ""
    }

}