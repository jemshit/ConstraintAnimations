package com.jemshit.constraintanimations.animation_activities

import android.os.Bundle
import android.support.constraint.ConstraintSet
import android.support.transition.TransitionManager
import android.support.v7.app.AppCompatActivity
import com.jemshit.constraintanimations.R
import com.jemshit.constraintanimations.toPx
import kotlinx.android.synthetic.main.activity_one.*

// Ref: https://proandroiddev.com/creating-awesome-animations-using-constraintlayout-and-constraintset-part-i-390cc72c5f75
// Tip: ConstraintLayout will only perform animation on its direct children. It may not handle ViewGroups well

class TwoActivity : AppCompatActivity() {

    // Tip: It lets you create and save constraints, and apply them to an existing ConstraintLayout.
    private val applyConstraintSet by lazy {
        ConstraintSet()
    }

    private val resetConstraintSet by lazy {
        ConstraintSet()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_two)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        applyConstraintSet.clone(constraintLayout)
        resetConstraintSet.clone(constraintLayout)

        animate2()

        resetButton.setOnClickListener {
            TransitionManager.beginDelayedTransition(constraintLayout)
            resetConstraintSet.applyTo(constraintLayout)
        }
    }

    // We have our android logo at the top of our screen. On tapping the image, it has to come to the bottom of the screen.
    // Also, notice that when the image reaches bottom of the screen, it’s size is increased.
    private fun animate1() {
        applyButton.setOnClickListener {
            TransitionManager.beginDelayedTransition(constraintLayout)

            applyConstraintSet.clear(R.id.image_logo, ConstraintSet.TOP)
            applyConstraintSet.constrainWidth(R.id.image_logo, 200.toPx)
            applyConstraintSet.constrainHeight(R.id.image_logo, 200.toPx)
            applyConstraintSet.connect(R.id.image_logo, ConstraintSet.BOTTOM, R.id.applyButton, ConstraintSet.TOP, 16.toPx)

            applyConstraintSet.applyTo(constraintLayout)
        }
    }

    // Tip: Use Layout as 2nd state !!!
    // make the transition from one layout’s constraints to the other.
    private fun animate2() {
        applyConstraintSet.clone(this, R.layout.activity_two_state_2)

        applyButton.setOnClickListener {
            TransitionManager.beginDelayedTransition(constraintLayout)
            applyConstraintSet.applyTo(constraintLayout)
        }
    }

    // Tip: clone
    //    But when you clone the constraints, it takes only the layout related constraints and attributes
    //    such as layout width and height. But not the styling attributes — such as textSize and stuff.
    //    So you need not replicate the exact styling in all the alternate layouts.

    // Tip: TransitionManager  
    //    this class manages the set of transitions that fire when there is a change of Scene.
    //    Setting specific transitions for scene changes is not required; by default, a Scene change will use
    //    AutoTransition to do something reasonable for most situations. Specifying other transitions for
    //    particular scene changes is only necessary if the application wants different transition behavior in these situations

    // Tip: beginDelayedTransition()
    //    convenience method to animate, using the default transition, to a new
    //    scene defined by all changes within the given scene root between calling this method and the next rendering frame.
}
