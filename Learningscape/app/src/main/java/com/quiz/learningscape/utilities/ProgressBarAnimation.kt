package com.quiz.learningscape.utilities

import android.content.Context
import android.view.animation.Animation
import android.view.animation.Transformation
import android.widget.ProgressBar
import android.widget.TextView

class ProgressBarAnimation(var  context:Context,var progressBar: ProgressBar,var textView: TextView,var from: Float,var  to: Float) : Animation() {

    override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
        super.applyTransformation(interpolatedTime, t)
        val value:Float = (to-from)*interpolatedTime
        progressBar.setProgress(value.toInt())
        textView.setText("$value%")

    }

    override fun initialize(width: Int, height: Int, parentWidth: Int, parentHeight: Int) {
        super.initialize(width, height, parentWidth, parentHeight)
    }

}
