package com.quiz.learningscape.activity

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.Transformation
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.quiz.learningscape.R
import com.quiz.learningscape.utilities.CheckNetwork
import com.shashank.sony.fancygifdialoglib.FancyGifDialog
import kotlinx.android.synthetic.main.activity_study.*
import android.provider.Settings

class StudyActivity : AppCompatActivity() {


    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_study)

        study_webview.settings.javaScriptEnabled = true
        study_webview.settings.setSupportZoom(false)

        val i: Intent = intent
        val url: String = i.getStringExtra("file") ?: ""
        setLoaderAnimate()
        study_webview.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                endLoaderAnimate()
            }

            override fun onReceivedError(
                view: WebView?,
                request: WebResourceRequest?,
                error: WebResourceError?
            ) {
                super.onReceivedError(view, request, error)
                endLoaderAnimate()
                showDialog()

            }
        }


        study_webview.loadUrl("file:///android_asset/$url.html")

    }

    private fun endLoaderAnimate() {
        level_view_study.clearAnimation()
        level_view_study.visibility = View.GONE
    }

    private fun setLoaderAnimate() {

        val objectAnimation = object : Animation() {

            override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
                val startHeight = 150
                val newHeight = (startHeight + (startHeight + 50) * interpolatedTime).toInt()
                level_view_study.layoutParams.height = newHeight
                level_view_study.requestLayout()//to change on layout changes
            }


            override fun initialize(width: Int, height: Int, parentWidth: Int, parentHeight: Int) {
                super.initialize(width, height, parentWidth, parentHeight)
            }

            override fun willChangeBounds(): Boolean {
                return true
            }
        }

        objectAnimation.repeatCount = -1
        objectAnimation.repeatMode = ValueAnimator.REVERSE
        objectAnimation.duration = 1000
        level_view_study.startAnimation(objectAnimation)


    }


    private fun showDialog() {
        FancyGifDialog.Builder(this@StudyActivity)
            .setTitle("No internet connection")
            .setNegativeBtnBackground("#77FF4081")
            .setNegativeBtnText("Settings")
            .setPositiveBtnBackground("#df5d49")
            .setPositiveBtnText("Retry")

            .setGifResource(R.drawable.stars)
            .isCancellable(true)
            .OnPositiveClicked {
                this@StudyActivity.recreate()
            }
            .OnNegativeClicked {
                startActivity(Intent(Settings.ACTION_SETTINGS))
            }
            .build()
    }


    fun isNetWorkAvailable(): Boolean {

        return CheckNetwork().isNetworkAvailable(this@StudyActivity)

    }

    override fun onResume() {
        super.onResume()
        if (!isNetWorkAvailable()) {

            showDialog()
        }

    }
}