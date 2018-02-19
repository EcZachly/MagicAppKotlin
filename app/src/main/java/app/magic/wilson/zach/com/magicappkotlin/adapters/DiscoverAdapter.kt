package app.magic.wilson.zach.com.magicappkotlin.adapters

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import app.magic.wilson.zach.com.magicappkotlin.R

/**
 * An adapter to display the different groupings of card suggestions on the Discover screen.
 */

class DiscoverAdapter(private var activity: Activity, private var groups: Array<String>): RecyclerView.Adapter<DiscoverAdapter.ViewHolder>(){

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
        return ViewHolder(itemView, imageView)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {

//        TODO("Update images with custome images and content description values")
//        TODO("Implement a click listener that launches a new activity or fragment when the user clicks a selected item")
    }

    class ViewHolder(row: View?, imageView: ImageView) :RecyclerView.ViewHolder(row){
        val groupDisplayImage: ImageView

        init {
            this.groupDisplayImage = imageView
        }
    }
}