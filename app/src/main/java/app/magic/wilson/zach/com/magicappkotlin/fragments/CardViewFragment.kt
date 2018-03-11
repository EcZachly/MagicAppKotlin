package app.magic.wilson.zach.com.magicappkotlin.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import app.magic.wilson.zach.com.magicappkotlin.R
import app.magic.wilson.zach.com.magicappkotlin.constants.Keys
import app.magic.wilson.zach.com.magicappkotlin.models.Card
import com.bumptech.glide.Glide
import com.google.gson.Gson


/**
 * A Fragment to display the Card in the DetailCardViewActivity.
 */
class CardViewFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_card_view, container, false)
        val cardDisplayImage = view.findViewById<ImageView>(R.id.detail_card_image)

        val json = arguments.getString(Keys.CARD_KEY)
        val card = Gson().fromJson<Card>(json, Card::class.java)

        val position = arguments.getInt(Keys.POSITION_KEY)

        val defaultImg = activity.getDrawable(R.drawable.magic_card_default)
        var imageUrl = ""
        if(card.imageURLs != null && !card.imageURLs.isEmpty()) {
            val imageList = card.imageURLs["en"].orEmpty()
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

    companion object {

        fun newInstance(card: Card, position: Int?): CardViewFragment {

            val args = Bundle()
            val jsonCard = Gson().toJson(card)

            args.putString(Keys.CARD_KEY, jsonCard)
            args.putInt(Keys.POSITION_KEY, position?: 0)

            val fragment = CardViewFragment()
            fragment.arguments = args

            return fragment
        }
    }

}
