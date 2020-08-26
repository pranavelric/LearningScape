package com.quiz.learningscape.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.quiz.learningscape.R
import com.quiz.learningscape.utilities.CheckNetwork
import com.shashank.sony.fancygifdialoglib.FancyGifDialog
import kotlinx.android.synthetic.main.activity_privacy_policy.*
import kotlinx.android.synthetic.main.activity_study.*

class PrivacyPolicyActivity : AppCompatActivity() {
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_privacy_policy)
        setSupportActionBar(privacy_toolbar)
        supportActionBar?.title = "Privacy policy"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        privacy_webview.settings.setSupportZoom(false)
        privacy_webview.settings.javaScriptEnabled = true

        privacy_webview.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)

            }

            override fun onReceivedError(
                view: WebView?,
                request: WebResourceRequest?,
                error: WebResourceError?
            ) {
                super.onReceivedError(view, request, error)

                showDialog()

            }
        }


        privacy_webview.loadUrl("file:///android_asset/privacypolicy.html")


    }


    private fun showDialog() {
        FancyGifDialog.Builder(this@PrivacyPolicyActivity)
            .setTitle("No internet connection")
            .setNegativeBtnBackground("#77FF4081")
            .setNegativeBtnText("Settings")
            .setPositiveBtnBackground("#df5d49")
            .setPositiveBtnText("Retry")

            .setGifResource(R.drawable.stars)
            .isCancellable(true)
            .OnPositiveClicked {
                this@PrivacyPolicyActivity.recreate()
            }
            .OnNegativeClicked {
                startActivity(Intent(Settings.ACTION_SETTINGS))
            }
            .build()
    }


    fun isNetWorkAvailable(): Boolean {

        return CheckNetwork().isNetworkAvailable(this@PrivacyPolicyActivity)

    }

    override fun onResume() {
        super.onResume()
        if (!isNetWorkAvailable()) {

            showDialog()
        }

    }
}