package app.magic.wilson.zach.com.magicappkotlin.adapters

import android.app.Activity
import android.support.v4.app.FragmentManager
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v7.preference.PreferenceManager
import app.magic.wilson.zach.com.magicappkotlin.R
import app.magic.wilson.zach.com.magicappkotlin.fragments.DetailCardPrintFragment
import app.magic.wilson.zach.com.magicappkotlin.models.Card

/**
 * An Adapter to display the CardViewFragments for the DetailCardViewActivity.
 */

class DetailCardPagerAdapter(activity: Activity, fragmentManager: FragmentManager, private val card: Card) : FragmentStatePagerAdapter(fragmentManager) {

    val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity)
    val filterLanguage = sharedPreferences.getString(activity.getString(R.string.settings_language_key), activity.getString(R.string.language_en))

    private val mImagesList = card.imageURLs.orEmpty()[filterLanguage]

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