package app.magic.wilson.zach.com.magicappkotlin.adapters

import android.app.Activity
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

class CardAdapter(private var activity: Activity, private var cards: List<Card>): RecyclerView.Adapter<CardAdapter.ViewHolder>(){

    override fun getItemCount(): Int {
        return cards.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent?.getContext())
                .inflate(R.layout.item_card, parent, false)
        val imageView = itemView?.findViewById<ImageView>(R.id.cardImage) as ImageView
        return ViewHolder(itemView, imageView)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val card = cards[position]
        // TODO("Set content descriptions to be the name of the card")

        // If the image(s) exist(s)
        if(card.imageURLs != null && !card.imageURLs.isEmpty()) {
            val image_list = card.imageURLs["en"].orEmpty()
            val image_url: String
            if (image_list.isNotEmpty()) {
                image_url = image_list[0]
            } else {
                image_url = ""
            }

            Glide.with(activity)
                    .load(image_url)
                    .into(holder?.cardDisplayImage)
        } else { // If the images do not exist
            // TODO("Populate the ImageView with a custom missing image graphic")
        }
    }

    class ViewHolder(row: View?, imageView: ImageView) :RecyclerView.ViewHolder(row){
        val cardDisplayImage: ImageView

        init {
            this.cardDisplayImage = imageView
        }
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

}