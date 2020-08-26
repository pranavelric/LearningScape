package com.quiz.learningscape.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import com.quiz.learningscape.R
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception
import java.util.*
import kotlin.concurrent.schedule

class MainActivity : AppCompatActivity() {
//    lateinit var image: ImageView;
//    lateinit var labelView: LoadingView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val animation = AnimationUtils.loadAnimation(this,
            R.anim.fade_in
        )

        imageView.startAnimation(animation)
        Timer().schedule(4000) {
            try {

                startActivity(Intent(this@MainActivity, HomeActivity::class.java))
                finish()
            } catch (e: Exception) {

            }
        }

    }
}