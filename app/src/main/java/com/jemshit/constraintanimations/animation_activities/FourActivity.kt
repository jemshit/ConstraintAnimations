package com.jemshit.constraintanimations.animation_activities

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.constraint.ConstraintSet
import android.support.transition.ChangeBounds
import android.support.transition.TransitionManager
import android.support.v7.app.AppCompatActivity
import android.view.animation.OvershootInterpolator
import com.jemshit.constraintanimations.R
import kotlinx.android.synthetic.main.activity_four.*

// Ref: https://proandroiddev.com/creating-awesome-animations-using-constraintlayout-and-constraintset-part-3-70558f05835a

class FourActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_four)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setupAnimations()
    }


    private fun setupAnimations() {
        askSize.setOnClickListener {
            updateConstraints(R.layout.activity_four_state_2)
            askSize.setText("ADD TO CART - 1234 INR")
        }

        close.setOnClickListener {
            updateConstraints(R.layout.activity_four)
            askSize.setText("SELECT SIZE")
        }
    }


    fun updateConstraints(@LayoutRes id: Int) {
        val newConstraintSet = ConstraintSet()
        newConstraintSet.clone(this, id)
        newConstraintSet.applyTo(shoppingRoot)

        val transition = ChangeBounds()
        transition.interpolator = OvershootInterpolator()
        TransitionManager.beginDelayedTransition(shoppingRoot, transition)
    }

}

