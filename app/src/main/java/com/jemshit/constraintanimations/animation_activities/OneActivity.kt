package com.jemshit.constraintanimations.animation_activities

import android.os.Bundle
import android.support.constraint.ConstraintSet
import android.support.constraint.solver.widgets.ConstraintWidget
import android.support.transition.TransitionManager
import android.support.v7.app.AppCompatActivity
import android.view.View.GONE
import com.jemshit.constraintanimations.R
import com.jemshit.constraintanimations.toPx
import kotlinx.android.synthetic.main.activity_one.*

// Ref: http://www.uwanttolearn.com/android/constraint-layout-animations-dynamic-constraints-ui-java-hell/
// Tip: use separate layout for different states of view
// Tip: constraintSetState2.clone(context, R.layout.state2)

class OneActivity : AppCompatActivity() {

    private val applyConstraintSet by lazy {
        ConstraintSet()
    }

    private val resetConstraintSet by lazy {
        ConstraintSet()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_one)

        // Tip: you can think like this API will remember all the constraints which we implement in XML
        applyConstraintSet.clone(constraintLayout)
        resetConstraintSet.clone(constraintLayout)

        animate6()

        resetButton.setOnClickListener {
            TransitionManager.beginDelayedTransition(constraintLayout)  // ToDo: can be extension for .applyTo
            resetConstraintSet.applyTo(constraintLayout)
        }
    }

    // I want to animate button 1, align left of a parent
    private fun animate1() {
        applyButton.setOnClickListener {
            TransitionManager.beginDelayedTransition(constraintLayout)  // Tip: put in beginning if u animate dynamically

            applyConstraintSet.setMargin(R.id.button1, ConstraintSet.START, 8.toPx)

            // Tip: you apply constraint of children it to constraintLayout
            applyConstraintSet.applyTo(constraintLayout)
        }
    }

    // I want to animate all buttons, center horizontal in parent
    private fun animate2() {
        applyButton.setOnClickListener {
            TransitionManager.beginDelayedTransition(constraintLayout)

            // Clear margins
            applyConstraintSet.setMargin(R.id.button1, ConstraintSet.START, 0)
            applyConstraintSet.setMargin(R.id.button1, ConstraintSet.END, 0)
            applyConstraintSet.setMargin(R.id.button2, ConstraintSet.START, 0)
            applyConstraintSet.setMargin(R.id.button2, ConstraintSet.END, 0)
            applyConstraintSet.setMargin(R.id.button3, ConstraintSet.START, 0)
            applyConstraintSet.setMargin(R.id.button3, ConstraintSet.END, 0)

            // center horizontally
            applyConstraintSet.centerHorizontally(R.id.button1, R.id.constraintLayout)
            applyConstraintSet.centerHorizontally(R.id.button2, R.id.constraintLayout)
            applyConstraintSet.centerHorizontally(R.id.button3, R.id.constraintLayout)

            applyConstraintSet.applyTo(constraintLayout)
        }
    }

    // I want to animate button 3 to center in parent
    private fun animate3() {
        applyButton.setOnClickListener {
            TransitionManager.beginDelayedTransition(constraintLayout)

            // Clear margins
            // Tip:  NOT Left, Right!
            applyConstraintSet.setMargin(R.id.button3, ConstraintSet.START, 0)
            applyConstraintSet.setMargin(R.id.button3, ConstraintSet.END, 0)
            applyConstraintSet.setMargin(R.id.button3, ConstraintSet.TOP, 0)
            applyConstraintSet.setMargin(R.id.button3, ConstraintSet.BOTTOM, 0)

            // center in parent
            applyConstraintSet.centerHorizontally(R.id.button3, R.id.constraintLayout)
            applyConstraintSet.centerVertically(R.id.button3, R.id.constraintLayout)

            applyConstraintSet.applyTo(constraintLayout)
        }
    }

    // I want to animate all buttons width from current size to 120dp
    private fun animate4() {
        applyButton.setOnClickListener {
            TransitionManager.beginDelayedTransition(constraintLayout)

            applyConstraintSet.constrainWidth(R.id.button1, 120.toPx)
            applyConstraintSet.constrainWidth(R.id.button1, 120.toPx)
            applyConstraintSet.constrainWidth(R.id.button2, 120.toPx)
            applyConstraintSet.constrainWidth(R.id.button2, 120.toPx)
            applyConstraintSet.constrainWidth(R.id.button3, 120.toPx)
            applyConstraintSet.constrainWidth(R.id.button3, 120.toPx)

            applyConstraintSet.applyTo(constraintLayout)
        }
    }

    // I want to animate button1 width and height to whole screen and all other views should hide
    private fun animate5() {
        applyButton.setOnClickListener {
            TransitionManager.beginDelayedTransition(constraintLayout)

            // Visibility
            applyConstraintSet.setVisibility(R.id.button2, GONE)
            applyConstraintSet.setVisibility(R.id.button3, GONE)

            // Tip: clear all constraints (including width, height)
            applyConstraintSet.clear(R.id.button1)

            // Tip: add New Constraint
            applyConstraintSet.connect(R.id.button1, ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT, 0)
            applyConstraintSet.connect(R.id.button1, ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, 0)
            applyConstraintSet.connect(R.id.button1, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, 0)
            applyConstraintSet.connect(R.id.button1, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 68.toPx)

            applyConstraintSet.applyTo(constraintLayout)
        }
    }

    // I want to animate all buttons, align to each other, top of the screen and horizontal
    private fun animate6() {
        applyButton.setOnClickListener {
            TransitionManager.beginDelayedTransition(constraintLayout)

            applyConstraintSet.clear(R.id.button1)
            applyConstraintSet.clear(R.id.button2)
            applyConstraintSet.clear(R.id.button3)

            // set width and height
            applyConstraintSet.constrainWidth(R.id.button1, ConstraintSet.WRAP_CONTENT);
            applyConstraintSet.constrainWidth(R.id.button2, ConstraintSet.WRAP_CONTENT);
            applyConstraintSet.constrainWidth(R.id.button3, ConstraintSet.WRAP_CONTENT);

            applyConstraintSet.constrainHeight(R.id.button1, ConstraintSet.WRAP_CONTENT);
            applyConstraintSet.constrainHeight(R.id.button2, ConstraintSet.WRAP_CONTENT);
            applyConstraintSet.constrainHeight(R.id.button3, ConstraintSet.WRAP_CONTENT);

            // Tip: Not necessary to connect manually!
            // button1 left and top align to parent
            /* applyConstraintSet.connect(R.id.button1, ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT)
             applyConstraintSet.connect(R.id.button1, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP)
             applyConstraintSet.connect(R.id.button1, ConstraintSet.RIGHT, R.id.button2, ConstraintSet.LEFT)

             // Tip: Bi-direction or Chaining
             applyConstraintSet.connect(R.id.button2, ConstraintSet.LEFT, R.id.button1, ConstraintSet.RIGHT)
             applyConstraintSet.connect(R.id.button2, ConstraintSet.RIGHT, R.id.button3, ConstraintSet.LEFT)

             // button3 right and top align to parent
             applyConstraintSet.connect(R.id.button3, ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT)
             applyConstraintSet.connect(R.id.button3, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP)
             applyConstraintSet.connect(R.id.button3, ConstraintSet.LEFT, R.id.button2, ConstraintSet.RIGHT)*/

            // Tip: Chain
            applyConstraintSet.createHorizontalChain(
                    ConstraintSet.PARENT_ID,            // Left id
                    ConstraintSet.LEFT,                 // Left side
                    ConstraintSet.PARENT_ID,            // Right id
                    ConstraintSet.RIGHT,                // Right side
                    intArrayOf(R.id.button1, R.id.button2, R.id.button3), // Chain Ids
                    null,                       // weights
                    ConstraintWidget.CHAIN_PACKED)

            applyConstraintSet.setHorizontalBias(R.id.button1, .3f)

            applyConstraintSet.applyTo(constraintLayout)
        }
    }
}
