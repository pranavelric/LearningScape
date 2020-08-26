package com.quiz.learningscape.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.quiz.learningscape.Adapters.CustomAdapterCategories
import com.quiz.learningscape.R
import kotlinx.android.synthetic.main.activity_categories.*

class Categories : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)
        setSupportActionBar(categories_toolbar)
        supportActionBar?.title = "Sample Papers"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        categories_recyclerView.layoutManager = LinearLayoutManager(this@Categories)

        val title: MutableList<String> = ArrayList()
        val htmlFile: MutableList<String> = ArrayList()
        title.add("Computer Science Mcq")
        htmlFile.add("generalComputer")
        val adapter = CustomAdapterCategories(this@Categories, title, htmlFile)
        categories_recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()

pdf.setOnClickListener { startActivity(Intent(this@Categories,PdfViewActivity::class.java)) }


    }
}