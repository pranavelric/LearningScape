package com.quiz.learningscape.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.quiz.learningscape.Adapters.CustomAdapterCategories
import com.quiz.learningscape.R
import com.quiz.learningscape.modal.CategoryModal
import kotlinx.android.synthetic.main.activity_quiz.*


class QuizActivity : AppCompatActivity() {


    private var firestore: FirebaseFirestore? = null
    val list: MutableList<String> = ArrayList()

    val urlList: MutableList<String> = ArrayList()

    private var adapter: CustomAdapterCategories? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        setSupportActionBar(quiz_toolbar)
        supportActionBar?.title = "Quizzes"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        firestore = FirebaseFirestore.getInstance();
        loadData()

        adapter = CustomAdapterCategories(this@QuizActivity, list)
        quiz_categories_recyclerview.adapter = adapter
        quiz_categories_recyclerview.layoutManager = LinearLayoutManager(this@QuizActivity)
        adapter!!.notifyDataSetChanged()

    }

    private fun loadData() {
        list.clear()
        firestore!!.collection("Quiz").document("Categories")
            .get().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val doc: DocumentSnapshot? = task.getResult()
                    if (doc != null && doc.exists()) {
                        val count = doc.get("Count") as Long

                        for (i in 1..count) {
                            val catName =
                                doc.getString("Cat" + i.toString())



                            list.add(catName!!)


                        }



                        adapter!!.notifyDataSetChanged()

                    } else {
                        Toast.makeText(
                            this@QuizActivity,
                            "No Category Document Exists!",
                            Toast.LENGTH_SHORT
                        ).show()
                        finish()
                    }
                } else {
                    Toast.makeText(
                        this@QuizActivity,
                        task.exception?.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    override fun onBackPressed() {
        startActivity(
            Intent(
                this@QuizActivity,
                HomeActivity::class.java
            )
        )
    }


}