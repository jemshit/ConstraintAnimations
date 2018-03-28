package com.jemshit.constraintanimations.animation_activities

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.constraint.ConstraintSet
import android.support.transition.ChangeBounds
import android.support.transition.TransitionManager
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.animation.OvershootInterpolator
import com.jemshit.constraintanimations.R
import kotlinx.android.synthetic.main.activity_three.*

// Ref: https://proandroiddev.com/creating-awesome-animations-using-constraintlayout-and-constraintset-part-2-5bbbae8ab24

class ThreeActivity : AppCompatActivity() {

    private var selectedView: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_three)
        setupAnimations()
    }

    private fun setupAnimations() {
        selectedView = null

        constraintLayout.setOnClickListener {
            toDefaultConstraints()
        }

        javaImg.setOnClickListener {
            if (selectedView != javaImg) {
                updateConstraints(R.layout.activity_three_state_java)
                selectedView = javaImg
            } else
                toDefaultConstraints()
        }

        kotlinImg.setOnClickListener {
            if (selectedView != kotlinImg) {
                updateConstraints(R.layout.activity_three_state_kotlin)
                selectedView = kotlinImg
            } else
                toDefaultConstraints()
        }
    }

    private fun toDefaultConstraints() {
        if (selectedView != null) {
            updateConstraints(R.layout.activity_three)
            selectedView = null
        }
    }

    fun updateConstraints(@LayoutRes id: Int) {
        val newConstraintSet = ConstraintSet()
        newConstraintSet.clone(this, id)
        newConstraintSet.applyTo(constraintLayout)

        // Tip: Transition with Interpolator!
        val transition = ChangeBounds()     // Tip: default AutoTransition
        transition.interpolator = OvershootInterpolator()
        TransitionManager.beginDelayedTransition(constraintLayout, transition)      // Tip: put at the end if u use state xml

        // Tip: AutoTransition
        //    which is a TransitionSet which first fades out disappearing targets, then moves and resizes existing
        //    targets, and finally fades in appearing targets.
    }
}