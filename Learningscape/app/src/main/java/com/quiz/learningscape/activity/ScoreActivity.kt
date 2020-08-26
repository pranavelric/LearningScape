package com.quiz.learningscape.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.quiz.learningscape.R
import kotlinx.android.synthetic.main.activity_score.*

class ScoreActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)


        val i: Intent = intent
        scoreGet.text = i.getIntExtra("score", 0).toString()+"pt"
        correct_cnt.text = i.getIntExtra("score", 0).toString()
        incorrect_cnt.text = (i.getIntExtra("total", 0) - i.getIntExtra("score", 0)).toString()

        totalScore.text = i.getIntExtra("total", 0).toString()
        done.setOnClickListener {
            startActivity(
                Intent(
                    this@ScoreActivity,
                    QuizActivity::class.java
                )
            )
        }






        back_btn.setOnClickListener {
            startActivity(
                Intent(
                    this@ScoreActivity,
                    QuizActivity::class.java
                )
            )
        }

    }

    override fun onBackPressed() {
        startActivity(
            Intent(
                this@ScoreActivity,
                QuizActivity::class.java
            )
        )

    }

}