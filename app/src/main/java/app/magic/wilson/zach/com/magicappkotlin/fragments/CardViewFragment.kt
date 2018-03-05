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
        val cardNameTv = view.findViewById<TextView>(R.id.detail_card_name)

        val json = arguments.getString(Keys.CARD_KEY)
        val card = Gson().fromJson<Card>(json, Card::class.java)

        cardNameTv.text = card.name

        val defaultImg = activity.getDrawable(R.drawable.magic_card_default)
        var imageUrl = ""
        if(card.imageURLs != null && !card.imageURLs.isEmpty()) {
            val imageList = card.imageURLs["en"].orEmpty()
            if (imageList.isNotEmpty()) {
                imageUrl = imageList[0]
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

        fun newInstance(card: Card): CardViewFragment {

            val args = Bundle()
            val jsonCard = Gson().toJson(card)

            args.putString(Keys.CARD_KEY, jsonCard)

            val fragment = CardViewFragment()
            fragment.arguments = args

            return fragment
        }
    }

}
