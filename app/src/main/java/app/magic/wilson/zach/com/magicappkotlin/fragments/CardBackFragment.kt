package app.magic.wilson.zach.com.magicappkotlin.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.magic.wilson.zach.com.magicappkotlin.R

/**
 * A fragment representing the back of the card.
 */
class CardBackFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_card_back_view, container, false)
    }
}