package com.quiz.learningscape.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.quiz.learningscape.Adapters.SetsAdapter
import com.quiz.learningscape.R
import kotlinx.android.synthetic.main.activity_sets.*


class SetsActivity : AppCompatActivity() {

    companion object {
        var catId: String? = ""
    }

    private var firestore: FirebaseFirestore? = null
    var setsIDs: MutableList<String> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sets)
        setSupportActionBar(toolbar_set)
        supportActionBar?.title = intent.getStringExtra("category") ?: "Quiz"
        catId = intent.getStringExtra("catId") ?: "1"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        level_view_set.visibility = View.VISIBLE

        set_recycler_view.layoutManager = GridLayoutManager(this@SetsActivity, 4)

        firestore = FirebaseFirestore.getInstance();

        loadSets();


    }


    fun loadSets() {
        setsIDs.clear()
        firestore!!.collection("Quiz").document("Cat${catId}")
            .get().addOnSuccessListener { documentSnapshot ->
                val noOfSets = documentSnapshot["Sets"] as Long


                val adapter = SetsAdapter(noOfSets.toInt(), this@SetsActivity)
                set_recycler_view.adapter = adapter
                level_view_set.visibility = View.GONE
            }
            .addOnFailureListener { e ->
                Toast.makeText(this@SetsActivity, e.message, Toast.LENGTH_SHORT).show()
                level_view_set.visibility = View.GONE
            }
    }

}