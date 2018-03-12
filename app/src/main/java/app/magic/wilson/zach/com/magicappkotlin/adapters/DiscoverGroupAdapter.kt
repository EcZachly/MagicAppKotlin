package app.magic.wilson.zach.com.magicappkotlin.adapters

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import app.magic.wilson.zach.com.magicappkotlin.R
import com.bumptech.glide.Glide

/**
 * An adapter to display the different groupings of card suggestions on the Discover screen.
 */

class DiscoverGroupAdapter(private var activity: Activity, private var groups: Array<String>): RecyclerView.Adapter<DiscoverGroupAdapter.ViewHolder>(){

    override fun getItemCount(): Int {
        return groups.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent?.getContext())
                .inflate(R.layout.item_discover_group, parent, false)
        val imageView = itemView?.findViewById<ImageView>(R.id.discover_group_image) as ImageView
        val textView = itemView.findViewById<TextView>(R.id.discover_group_lbl) as TextView
        return ViewHolder(itemView, imageView, textView)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {

        val cardGrouping = groups[position]

        // If it's the colors list (only the colors list contains the string "red"), populate the custom images
        if(groups.contains(activity.getString(R.string.color_red))){
            populateCustomImages(holder, position, R.array.discover_colors_array)
        } else if (groups.contains(activity.getString(R.string.rarity_common))) {
            // If it's the rarities list (only the rarities list contains the string "common"), populate the custom images
            populateCustomImages(holder, position, R.array.discover_rarities_array)
            holder?.groupDisplayLbl?.text = cardGrouping
        } else {
            holder?.groupDisplayLbl?.text = cardGrouping
        }
        holder?.groupDisplayImage?.contentDescription = activity.getString(R.string.group_cd, cardGrouping)

//        TODO("Implement a click listener that launches a new activity or fragment when the user clicks a selected item")
    }

    // A helper method to display custom images that go with an array saved to Resources
    private fun populateCustomImages(holder: ViewHolder?, position: Int, arrayId: Int){
        val imagesArray = activity.resources.obtainTypedArray(arrayId)
        Glide.with(activity)
                .load(imagesArray.getResourceId(position, -1))
                .into(holder?.groupDisplayImage)
        imagesArray.recycle()
    }

    class ViewHolder(row: View?, imageView: ImageView, textView: TextView) :RecyclerView.ViewHolder(row){
        val groupDisplayImage: ImageView
        val groupDisplayLbl: TextView

        init {
            this.groupDisplayImage = imageView
            this.groupDisplayLbl = textView
        }
    }
}