package app.magic.wilson.zach.com.magicappkotlin.adapters

import android.app.Activity
import android.support.v4.view.ViewPager
import android.support.v7.preference.PreferenceManager
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
class PrintThumbnailAdapter(private val activity: Activity, viewPager: ViewPager, private val card: Card): RecyclerTabLayout.Adapter<PrintThumbnailAdapter.ViewHolder>(viewPager) {

    private val mPrints: List<String>

    init {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity)
        val filterLanguage = sharedPreferences.getString(activity.getString(R.string.settings_language_key), activity.getString(R.string.language_en))

        mPrints = card.imageURLs.orEmpty()[filterLanguage].orEmpty()
    }

    override fun getItemCount(): Int {
        return mPrints.size + 2
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent?.context)
                .inflate(R.layout.item_thumbnail, parent, false)
        val imageView = itemView?.findViewById<ImageView>(R.id.thumbnail) as ImageView
        return ViewHolder(itemView, imageView)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.setIsRecyclable(false)
        holder?.bind(activity, mPrints, position)
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    inner class ViewHolder(row: View?, imageView: ImageView) : RecyclerView.ViewHolder(row){
        val cardDisplayImage = imageView

        fun bind(activity: Activity, prints: List<String>, position: Int) {

                val defaultImg = activity.getDrawable(R.drawable.magic_card_default)

                var imageUrl = ""
                if (!prints.isEmpty()) {
                    if (adapterPosition > 0 && adapterPosition <= prints.size) {
                        if (prints.isNotEmpty()) {
                            imageUrl = mPrints[position - 1]
                        }
                    } else {
                        // Hide the buffer on either side of the pages
                        this.cardDisplayImage.visibility = View.GONE
                    }
                }

                Glide.with(activity)
                        .load(imageUrl)
                        .placeholder(defaultImg)
                        .into(cardDisplayImage)

                cardDisplayImage.contentDescription = activity.getString(R.string.card_name_cd, card.name)

                cardDisplayImage.setOnClickListener { viewPager.currentItem = adapterPosition }
        }
    }
}
