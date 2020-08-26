package com.quiz.learningscape.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.quiz.learningscape.R
import com.quiz.learningscape.activity.Categories
import com.quiz.learningscape.activity.QuizActivity
import kotlinx.android.synthetic.main.fragment_home.view.*


class HomeFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v: View = inflater.inflate(R.layout.fragment_home, container, false)
        v.categories.setOnClickListener { startActivity(Intent(activity, Categories::class.java)) }
        v.quiz.setOnClickListener { startActivity(Intent(activity, QuizActivity::class.java)) }



        return v
    }


}