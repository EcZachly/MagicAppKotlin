package app.magic.wilson.zach.com.magicappkotlin.adapters

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import app.magic.wilson.zach.com.magicappkotlin.R
import app.magic.wilson.zach.com.magicappkotlin.models.Card
import com.bumptech.glide.Glide
import java.security.cert.PKIXRevocationChecker
import java.util.*

/**
 * Created by Zach on 2/10/18.
 */
class CardAdapter(private var activity: Activity, private var cards: List<Card>): RecyclerView.Adapter<CardAdapter.ViewHolder>(){
    override fun getItemCount(): Int {
        return cards.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent?.getContext())
                .inflate(R.layout.card_item, parent, false)
        val imageView = itemView?.findViewById(R.id.cardImage) as ImageView
        return ViewHolder(itemView, imageView)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val card = cards[position]
        Log.i("CARD", card.toString())
        if(card.imageURLs != null && !card.imageURLs.isEmpty()) {
            Log.i("image url", card.imageURLs.toString())
            val image_list = card.imageURLs["en"].orEmpty()
            val image_url: String
            if (image_list.isNotEmpty()) {
                image_url = image_list[0]
            } else {
                image_url = ""
            }
            Log.i("url", image_url)

            Glide.with(activity)
                    .load(image_url)
                    .into(holder?.holdImage)
        }
    }

    class ViewHolder(row: View?, imageView: ImageView) :RecyclerView.ViewHolder(row){
        val holdImage: ImageView

        init {
            this.holdImage = imageView
        }
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

}