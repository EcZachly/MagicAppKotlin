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
import java.util.*

/**
 * An adapter to display the preview images of different prints.
 */
class PrintThumbnailAdapter(private val activity: Activity, viewPager: ViewPager, private val card: Card): RecyclerTabLayout.Adapter<PrintThumbnailAdapter.ViewHolder>(viewPager) {

    private val mPrints: List<String>

    init {
        // TODO: Set the language in shared preferences so we don't have to recheck it
        // deviceLanguage is the language code
        val deviceLanguage = Locale.getDefault().language

        // validLanguages is a predetermined list of languages the app has data for and can support
        val validLanguages = activity.resources.getStringArray(R.array.card_languages_array)

        // if the language is supported, then filter the card results to that language.
        // Otherwise default to English
        val filterLanguage = if (validLanguages.contains(deviceLanguage)) deviceLanguage else "en"

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
