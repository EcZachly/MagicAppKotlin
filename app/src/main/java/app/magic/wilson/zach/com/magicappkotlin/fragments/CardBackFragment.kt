package app.magic.wilson.zach.com.magicappkotlin.fragments

import android.animation.Animator
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import app.magic.wilson.zach.com.magicappkotlin.R
import android.animation.AnimatorListenerAdapter
import android.support.v4.app.FragmentManager

/**
 * A fragment representing the back of the card.
 */
class CardBackFragment : Fragment() {
    private val LONG_ANIMATION_TIME: Long = 300
    private val ANIMATION_DELAY: Long = 300
    private val SHORT_ANIMATION_TIME: Long = 150

    protected lateinit var cardDataLayout: LinearLayout
    protected lateinit var cardBackImageView: ImageView

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_card_back_view, container, false)
        cardDataLayout = view.findViewById(R.id.card_back_text)
        cardBackImageView = view.findViewById<ImageView>(R.id.default_card_image_back)

        cardDataLayout.setAlpha(0f)
        cardDataLayout.visibility = View.VISIBLE

        cardDataLayout.animate()
                .alpha(1f)
                .setDuration(SHORT_ANIMATION_TIME)
                .setStartDelay(ANIMATION_DELAY)
                .setListener(null)

        cardBackImageView.animate()
                .alpha(0f)
                .setDuration(LONG_ANIMATION_TIME)
                .setStartDelay(ANIMATION_DELAY)
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        cardBackImageView.visibility = View.INVISIBLE
                    }
                })

//        // TODO: Populate fields with card data
        return view
    }

    fun flipBack(childFragmentManager: FragmentManager) {
        cardBackImageView.setAlpha(0f)
        cardBackImageView.visibility = View.VISIBLE

        cardBackImageView.animate()
                .alpha(1f)
                .setDuration(SHORT_ANIMATION_TIME)
                .setListener(null)

        cardDataLayout.animate()
                .alpha(0f)
                .setDuration(SHORT_ANIMATION_TIME)
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        cardDataLayout.visibility = View.INVISIBLE
                        childFragmentManager.popBackStack()
                    }
                })
    }
}