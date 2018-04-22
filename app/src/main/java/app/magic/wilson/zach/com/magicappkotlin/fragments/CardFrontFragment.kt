package app.magic.wilson.zach.com.magicappkotlin.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import app.magic.wilson.zach.com.magicappkotlin.R
import app.magic.wilson.zach.com.magicappkotlin.constants.Keys
import app.magic.wilson.zach.com.magicappkotlin.models.Card
import com.bumptech.glide.Glide
import com.google.gson.Gson

/**
* A fragment representing the front of the card.
*/
class CardFrontFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_card_front_view, container, false)
        val cardDisplayImage = view.findViewById<ImageView>(R.id.detail_card_image_front)

        val json = arguments.getString(Keys.CARD_KEY)
        val card = Gson().fromJson<Card>(json, Card::class.java)

        val position = arguments.getInt(Keys.POSITION_KEY)

        val defaultImg = activity.getDrawable(R.drawable.magic_card_default)
        var imageUrl = ""

        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity)
        val filterLanguage = sharedPreferences.getString(activity.getString(R.string.settings_language_key), activity.getString(R.string.language_en))

        if(card.imageURLs != null && !card.imageURLs.isEmpty()) {
            val imageList = card.imageURLs[filterLanguage].orEmpty()
            if (imageList.isNotEmpty()) {

                val imageIndex: Int
                when (position){
                // If the Fragment is being created for View at position 0, we want it to look like the last image in the list
                    0 -> imageIndex = imageList.size - 1
                // If the fragment is being created for View in the last position, we want it to look like the very first image in the list
                    imageList.size + 1 -> imageIndex = 0
                // All other images should be shifted by 1 because of the extra image in front
                    else -> imageIndex = position - 1
                }
                imageUrl = imageList[imageIndex]
            }
        }

        Glide.with(this)
                .load(imageUrl)
                .placeholder(defaultImg)
                .into(cardDisplayImage)

        cardDisplayImage.contentDescription = getString(R.string.card_name_cd, card.name)

        return view
    }
}