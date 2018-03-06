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
// TODO: Localize this so that you can get the cards for other languages than just EN
class PrintThumbnailAdapter(private val activity: Activity, viewPager: ViewPager, private val card: Card): RecyclerTabLayout.Adapter<PrintThumbnailAdapter.ViewHolder>(viewPager) {

    private val mPrints = card.imageURLs

    override fun getItemCount(): Int {
        return mPrints.orEmpty().get("en").orEmpty().size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent?.getContext())
                .inflate(R.layout.item_thumbnail, parent, false)
        val imageView = itemView?.findViewById<ImageView>(R.id.thumbnail) as ImageView
        return ViewHolder(itemView, imageView)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bind(activity, position)
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    inner class ViewHolder(row: View?, imageView: ImageView) : RecyclerView.ViewHolder(row){
        val cardDisplayImage: ImageView

        init {
            this.cardDisplayImage = imageView
        }

        fun bind(activity: Activity, position: Int) {

            val defaultImg = activity.getDrawable(R.drawable.magic_card_default)

            var imageUrl = ""
            if(card.imageURLs != null && !card.imageURLs.isEmpty()) {
                val imageList = card.imageURLs["en"].orEmpty()
                if (imageList.isNotEmpty()) {
                    imageUrl = imageList[position % imageList.size]
                }
            }

            Glide.with(activity)
                    .load(imageUrl)
                    .placeholder(defaultImg)
                    .into(cardDisplayImage)

            cardDisplayImage.contentDescription = activity.getString(R.string.card_name_cd, card.name)

            cardDisplayImage.setOnClickListener { viewPager.currentItem = adapterPosition}
        }
    }
}
