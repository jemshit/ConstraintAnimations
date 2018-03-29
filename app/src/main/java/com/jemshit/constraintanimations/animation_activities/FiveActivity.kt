package com.jemshit.constraintanimations.animation_activities

import android.os.Bundle
import android.support.constraint.ConstraintSet
import android.support.transition.ChangeBounds
import android.support.transition.TransitionManager
import android.support.v7.app.AppCompatActivity
import android.view.animation.AnticipateOvershootInterpolator
import com.jemshit.constraintanimations.R
import kotlinx.android.synthetic.main.activity_five.*

// Ref: https://android.jlelse.eu/build-awesome-animations-with-7-lines-of-code-using-constraintlayout-854e8fd3ad93

class FiveActivity : AppCompatActivity() {
    private var show = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_five)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        backgroundImage.setOnClickListener {
            if (show) hideComponents() else showComponents()
        }
    }

    private fun showComponents() {
        show = true

        val constraintSet = ConstraintSet()
        constraintSet.clone(this, R.layout.activity_five_state_2)

        val transition = ChangeBounds()
        transition.interpolator = AnticipateOvershootInterpolator(1.0f)
        transition.duration = 1200

        TransitionManager.beginDelayedTransition(constraint, transition)
        constraintSet.applyTo(constraint)
    }

    private fun hideComponents() {
        show = false

        val constraintSet = ConstraintSet()
        constraintSet.clone(this, R.layout.activity_five)

        val transition = ChangeBounds()
        transition.interpolator = AnticipateOvershootInterpolator(1.0f)
        transition.duration = 1200

        TransitionManager.beginDelayedTransition(constraint, transition)
        constraintSet.applyTo(constraint)
    }


    // Ref: https://robinhood.engineering/beautiful-animations-using-android-constraintlayout-eee5b72ecae3
    // Tip: duplicate xml style
    //    If you are defining the alternate XML file specifically for transition purposes, you can actually
    //    omit all of the non-layout styling attributes (e.g. textSize). The ConstraintSet simply captures
    //    all of the layout based constraints on each view and discard other attributes. This way, you won’t
    //    actually have to maintain a consistent styling across the two files (e.g. if you change the textSize
    //    on the original XML, you don’t have to do this in the alternate XML).

    // Tip: animates only layout constraint changes:
    //    ConstraintLayout only animates layout related changes. You can’t encode other changes in an
    //    alternate XML file (e.g. elevation change, text change) and expect the framework to do everything
    //    for you. ConstraintSet.clone(...) only copies over the layout/constraint changes and discards everything else.
    //    if you dynamically change a layout based attribute for an element inside ConstraintLayout
    //    (e.g. translationY), the animation won’t take the updated attribute into account. This means
    //    that when the animation is run, the attribute will instantly revert back to the original value,
    //    and then animate to the new value.
}

