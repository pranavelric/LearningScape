package com.quiz.learningscape.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.barteksc.pdfviewer.PDFView
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle
import com.quiz.learningscape.R
import kotlinx.android.synthetic.main.activity_pdf_view.*


class PdfViewActivity : AppCompatActivity(),OnPageChangeListener,OnLoadCompleteListener {

    private val TAG = MainActivity::class.java.simpleName
    val SAMPLE_FILE = "gk.pdf"

    var pageNumber = 0
    var pdfFileName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pdf_view)

        setSupportActionBar(pdf_toolbar)
        supportActionBar?.title="General Knowledge Questions"
        displayFromAsset(SAMPLE_FILE);


    }


    private fun displayFromAsset(assetFileName: String) {
        pdfFileName = assetFileName
        pdfView!!.fromAsset(SAMPLE_FILE)
            .defaultPage(pageNumber)
            .enableSwipe(true)
            .swipeHorizontal(false)
            .onPageChange(this)
            .enableAnnotationRendering(true)
            .onLoad(this)
            .scrollHandle(DefaultScrollHandle(this))
            .load()

    }


    override fun onPageChanged(page: Int, pageCount: Int) {

    }



    override fun loadComplete(nbPages: Int) {

    }
}