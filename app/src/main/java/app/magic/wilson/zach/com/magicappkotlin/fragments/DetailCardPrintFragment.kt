package app.magic.wilson.zach.com.magicappkotlin.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import app.magic.wilson.zach.com.magicappkotlin.R
import app.magic.wilson.zach.com.magicappkotlin.constants.Keys
import app.magic.wilson.zach.com.magicappkotlin.models.Card
import com.google.gson.Gson

// TODO: Viewpager is broken.  does not display different prints in card image area
// TODO: The scroll logic is all messed up.  take a look at the print order logic from looped/infinite scrolling


/**
 * A Fragment to display the Card in the DetailCardViewActivity.
 */
class DetailCardPrintFragment : Fragment() {

    /**
     * Whether or not we're showing the back of the card (otherwise showing the front).
     */
    private var mShowingBack = false


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_card_flip_view, container, false)

        if (savedInstanceState == null) {

            val frontFragment = CardFrontFragment()
            frontFragment.arguments = arguments

            fragmentManager
                    .beginTransaction()
                    .add(R.id.card_flip_container, frontFragment)
                    .commit()
        }
        setCardImgClickListener(view)
        return view
    }

    fun setCardImgClickListener(cardImageView: View){
        cardImageView.setOnClickListener {
            Toast.makeText(context, "card was clicked", Toast.LENGTH_SHORT).show()
            flipCard()
            // TODO: After flip, fade card away and display card information as text
        }
    }

    private fun flipCard() {
        if (mShowingBack) {
            fragmentManager.popBackStack()
            mShowingBack = false
            return
        }

        // Flip the card to the back
        mShowingBack = true

        // Add the back of card fragment and animate it so that the card looks like it was flipped
        fragmentManager
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
