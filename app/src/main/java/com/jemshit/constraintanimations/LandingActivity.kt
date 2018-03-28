package com.jemshit.constraintanimations

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.jemshit.constraintanimations.animation_activities.FourActivity
import com.jemshit.constraintanimations.animation_activities.OneActivity
import com.jemshit.constraintanimations.animation_activities.ThreeActivity
import com.jemshit.constraintanimations.animation_activities.TwoActivity
import kotlinx.android.synthetic.main.activity_landing.*

class LandingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)

        buttonOneActivity.setOnClickListener {
            startActivity(Intent(this, OneActivity::class.java))
        }
        buttonTwoActivity.setOnClickListener {
            startActivity(Intent(this, TwoActivity::class.java))
        }
        buttonThreeActivity.setOnClickListener {
            startActivity(Intent(this, ThreeActivity::class.java))
        }
        buttonFourActivity.setOnClickListener {
            startActivity(Intent(this, FourActivity::class.java))
        }
    }
}