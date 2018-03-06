package app.magic.wilson.zach.com.magicappkotlin.adapters

import android.app.Activity
import android.support.v4.view.ViewPager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import app.magic.wilson.zach.com.magicappkotlin.R
import app.magic.wilson.zach.com.magicappkotlin.models.Card
import com.bumptech.glide.Glide
import com.nshmura.recyclertablayout.RecyclerTabLayout

/**
 * An adapter to display the preview images of different prints.
 */

class PrintThumbnailAdapter(private val activity: Activity, viewPager: ViewPager, private val cards: List<Card>): RecyclerTabLayout.Adapter<PrintThumbnailAdapter.ViewHolder>(viewPager) {

    private val mAdapter = mViewPager.adapter as DetailCardPagerAdapter

    override fun getItemCount(): Int {
        return cards.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent?.getContext())
                .inflate(R.layout.item_thumbnail, parent, false)
        val imageView = itemView?.findViewById<ImageView>(R.id.thumbnail) as ImageView
        return ViewHolder(itemView, imageView)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bind(activity, cards[position])
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    inner class ViewHolder(row: View?, imageView: ImageView) : RecyclerView.ViewHolder(row){
        val cardDisplayImage: ImageView

        init {
            this.cardDisplayImage = imageView
        }

        fun bind(activity: Activity, card: Card) {

            val defaultImg = activity.getDrawable(R.drawable.magic_card_default)

            var image_url = ""
            if(card.imageURLs != null && !card.imageURLs.isEmpty()) {
                val image_list = card.imageURLs["en"].orEmpty()
                if (image_list.isNotEmpty()) {
                    image_url = image_list[0]
                }
            }

            Glide.with(activity)
                    .load(image_url)
                    .placeholder(defaultImg)
                    .into(cardDisplayImage)

            cardDisplayImage.contentDescription = activity.getString(R.string.card_name_cd, card.name)

            cardDisplayImage.setOnClickListener { viewPager.currentItem = adapterPosition}
        }
    }
}
