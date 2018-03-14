package app.magic.wilson.zach.com.magicappkotlin.adapters

import android.app.Activity
import android.support.v7.preference.PreferenceManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import app.magic.wilson.zach.com.magicappkotlin.R
import app.magic.wilson.zach.com.magicappkotlin.models.Card
import com.bumptech.glide.Glide

/**
 * An adapter to display the Magic card images.
 */

class CardAdapter(private var activity: Activity, private var cards: List<Card>, val clickListener: (Card) -> Unit): RecyclerView.Adapter<CardAdapter.ViewHolder>() {

    val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity)
    val filterLanguage = sharedPreferences.getString(activity.getString(R.string.settings_language_key), activity.getString(R.string.language_en))

    override fun getItemCount(): Int {
        return cards.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent?.getContext())
                .inflate(R.layout.item_card, parent, false)
        val imageView = itemView?.findViewById<ImageView>(R.id.cardImage) as ImageView
        return ViewHolder(itemView, imageView, filterLanguage)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bind(activity, cards[position], clickListener)
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    class ViewHolder(row: View?, imageView: ImageView, private val filterLanguage: String) :RecyclerView.ViewHolder(row){
        val cardDisplayImage: ImageView

        init {
            this.cardDisplayImage = imageView
        }

        fun bind(activity: Activity, card: Card, clickListener: (Card) -> Unit) {

            val defaultImg = activity.getDrawable(R.drawable.magic_card_default)

            var image_url = ""
            if(card.imageURLs != null && !card.imageURLs.isEmpty()) {
                val image_list = card.imageURLs[filterLanguage].orEmpty()
                if (image_list.isNotEmpty()) {
                    image_url = image_list[0]
                }
            }

            Glide.with(activity)
                    .load(image_url)
                    .placeholder(defaultImg)
                    .into(cardDisplayImage)

            cardDisplayImage.contentDescription = activity.getString(R.string.card_name_cd, card.name)

            itemView.setOnClickListener { clickListener(card)}
        }
    }
}