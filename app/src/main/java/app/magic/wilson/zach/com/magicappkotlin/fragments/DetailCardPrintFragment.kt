package app.magic.wilson.zach.com.magicappkotlin.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import app.magic.wilson.zach.com.magicappkotlin.R
import app.magic.wilson.zach.com.magicappkotlin.constants.Keys
import app.magic.wilson.zach.com.magicappkotlin.models.Card
import com.google.gson.Gson

/**
 * A Fragment to display the Card in the DetailCardViewActivity.
 */
class DetailCardPrintFragment : Fragment() {

    // Whether or not we're showing the back of the card (otherwise showing the front)
    private var mShowingBack = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_card_flip_view, container, false)

        if (savedInstanceState == null) {

            val frontFragment = CardFrontFragment()
            frontFragment.arguments = arguments

            childFragmentManager
                    .beginTransaction()
                    .add(R.id.card_flip_container, frontFragment)
                    .commit()
        }
        setCardImgClickListener(view)
        return view
    }

    fun setCardImgClickListener(cardImageView: View){
        cardImageView.setOnClickListener {
            flipCard()
        }
    }

    private fun flipCard() {
        if (mShowingBack) {

            val fragment: CardBackFragment = childFragmentManager.findFragmentById(R.id.card_flip_container) as CardBackFragment
            fragment.flipBack(childFragmentManager)
            mShowingBack = false
            return
        }

        // Flip the card to the back
        mShowingBack = true

        // Add the back of card fragment and animate it so that the card looks like it was flipped
        childFragmentManager
                .beginTransaction()
                .setCustomAnimations(
                        R.animator.card_flip_right_in,
                        R.animator.card_flip_right_out,
                        R.animator.card_flip_left_in,
                        R.animator.card_flip_left_out)
                .replace(R.id.card_flip_container, CardBackFragment())
                .addToBackStack(null)
                .commit()
    }

    companion object {

        fun newInstance(card: Card, position: Int?): DetailCardPrintFragment {

            val args = Bundle()
            val jsonCard = Gson().toJson(card)

            args.putString(Keys.CARD_KEY, jsonCard)
            args.putInt(Keys.POSITION_KEY, position?: 0)

            val fragment = DetailCardPrintFragment()
            fragment.arguments = args

            return fragment
        }
    }
}
