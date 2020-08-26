package com.quiz.learningscape.activity

import android.animation.Animator
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.material.button.MaterialButton
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.quiz.learningscape.R
import com.quiz.learningscape.activity.SetsActivity.Companion.catId
import com.quiz.learningscape.modal.QuestionModal
import kotlinx.android.synthetic.main.activity_quiz1.*


class Quiz1 : AppCompatActivity() {

    private var score = 0
    var count = 0

    lateinit var list: MutableList<QuestionModal>
    var position: Int = 0
    var timer: CountDownTimer = createTimer()
    private var firestore: FirebaseFirestore? = null
    var setNo = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz1)
        firestore = FirebaseFirestore.getInstance();

        list = ArrayList()



        setNo = intent.getIntExtra("setNo", 1)
        getQuestionsList()
        //   timer = createTimer()

        for (i in 0 until 4) {

            option_lay.getChildAt(i).setOnClickListener {

                    startTimer(timer, false)
                    checkAnswer(it as MaterialButton?, true)

            }
        }


        next_quest.isEnabled = false
        next_quest.alpha = 0.07F


        next_quest.setOnClickListener {

            next_quest.isEnabled = false
            next_quest.alpha = 0.07F
            enableOption(true)
            position++



            if (position == list.size) {
                val scoreIntent = Intent(this@Quiz1, ScoreActivity::class.java)
                scoreIntent.putExtra("score", score)
                scoreIntent.putExtra("total", list.size)
                startActivity(scoreIntent)

                finish()



                return@setOnClickListener

            }
            count = 0
            playAnim(question, 0, list[position].qestion)
            startTimer(timer, true)

        }

    }


    private fun enableOption(b: Boolean) {

        for (i in 0 until 4) {
            option_lay.getChildAt(i).isEnabled = b
            if (b) {
                option_lay.getChildAt(i).backgroundTintList = ColorStateList.valueOf(Color.GRAY)
            }
        }

    }

    private fun createTimer(): CountDownTimer {
        return object : CountDownTimer(10000, 1000) {
            override fun onFinish() {

                for (i in 0 until 4) {
                    checkAnswer(option_lay.getChildAt(i) as MaterialButton?, false)
                }
            }

            override fun onTick(millisUntilFinished: Long) {
                quiz_time_left.text = (millisUntilFinished / 1000).toString()
            }

        }
    }

    private fun startTimer(timer: CountDownTimer, start: Boolean) {

        if (start) {
            timer.start()
        } else {
            timer.cancel()
        }
    }


    private fun playAnim(view: View?, value: Int, data: String) {


        view?.animate()?.alpha(value.toFloat())?.scaleX(value.toFloat())?.scaleY(1F)
            ?.setDuration(500)
            ?.setStartDelay(50)
            ?.setInterpolator(DecelerateInterpolator())
            ?.setListener(object : Animator.AnimatorListener {
                override fun onAnimationRepeat(animation: Animator?) {

                }

                override fun onAnimationEnd(animation: Animator?) {
                    try {

                        (view as TextView).text = data
                        ques_num_counter.text = "${position + 1}/${list.size}"


                    } catch (e: Exception) {
                        e.printStackTrace()
                        (view as Button).text = data
                    }

                    view.tag = data
                    if (value != 1) {
                        playAnim(view, 1, data)

                    }
                }

                override fun onAnimationCancel(animation: Animator?) {

                }

                override fun onAnimationStart(animation: Animator?) {

                    if (value == 0 && count < 4) {
                        var option: String = ""
                        if (count == 0) {
                            option = list[position].option1
                        } else if (count == 1) {
                            option = list[position].option2
                        } else if (count == 2) {
                            option = list[position].option3
                        } else if (count == 3) {
                            option = list[position].option4
                        }
                        playAnim(option_lay.getChildAt(count), 0, option)
                        count++


                    }


                }


            })
    }


    private fun getQuestionsList() {
        list.clear()

        firestore?.collection("Quiz")?.document("Cat${catId}")
            ?.collection("Set${setNo}")?.get()
            ?.addOnSuccessListener(OnSuccessListener<QuerySnapshot> { queryDocumentSnapshots ->

                for (i in queryDocumentSnapshots) {
                    list.add(
                        QuestionModal(
                            i.getString("Question").toString(),
                            i.getString("A").toString(),
                            i.getString("B").toString(),
                            i.getString("C").toString(),
                            i.getString("D").toString(),
                            i.getString("Answer").toString()
                        )
                    )
                }
                if (list.size > 0) {

                    playAnim(question, 0, list[position].qestion)
                    startTimer(timer, true)
                }

            })
            ?.addOnFailureListener(OnFailureListener { e ->
                Toast.makeText(this@Quiz1, e.message, Toast.LENGTH_SHORT).show()
                //loadingDialog.dismiss()
            })
    }


    private fun checkAnswer(selectedOption: MaterialButton?, increase: Boolean) {


        enableOption(false)
        next_quest.isEnabled = true
        next_quest.alpha = 1F

        if (selectedOption?.text.toString() == list[position].correctAns) {
            if (increase) {
                score++
            }

            selectedOption?.backgroundTintList = ColorStateList.valueOf(Color.GREEN)
            //  StyleableToast.makeText(applicationContext, "Right Answer", R.style.Successtoast).show()
        } else {
            selectedOption?.backgroundTintList = ColorStateList.valueOf(Color.RED)
            // StyleableToast.makeText(applicationContext, "Wrong!!", R.style.Failtoast).show()

            val crctOption: MaterialButton =
                this@Quiz1.option_lay.findViewWithTag(list[position].correctAns)
            crctOption.backgroundTintList = ColorStateList.valueOf(Color.GREEN)

        }

    }

    override fun onBackPressed() {
        if (timer != null) {
            startTimer(timer, false)
        }
        startActivity(Intent(this@Quiz1, QuizActivity::class.java))
        finish()
    }

}