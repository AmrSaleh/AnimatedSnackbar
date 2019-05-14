package com.amrsaleh.animatedsnackbarlib

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Handler
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.snackbar_view.view.*


/**
 * An animated snack bar that can be auto-hidden after
 * [AUTO_HIDE_DELAY_MILLIS] milliseconds.
 *
 */
class AnimatedSnackbar(context: Context, attrs : AttributeSet) : LinearLayout(context, attrs) {

//    private var customBackground : Drawable? = null
//    set(value) {
//        field = value
//        if(value != null){
//            this.background = value
//            invalidate()
//            requestLayout()
//        }
//    }
    private var iconDrawable : Drawable? = context.getDrawable(android.R.drawable.ic_dialog_info)
    private var iconTint = Color.WHITE
    private var textTint = Color.WHITE

    private val mHideHandler = Handler()
    private val mHideRunnable = Runnable { hideSnackbar() }
    private var hidden = true

    init {
        iconDrawable = context.getDrawable(android.R.drawable.ic_dialog_info)

        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.AnimatedSnackbar,
            0, 0).apply {
                try {
                    iconDrawable = getDrawable(R.styleable.AnimatedSnackbar_icon)
                    iconTint = getColor(R.styleable.AnimatedSnackbar_icon_tint, Color.WHITE)
                    textTint = getColor(R.styleable.AnimatedSnackbar_text_color, Color.WHITE)
                } finally {
                    recycle()
            }
        }
        inflate(context, R.layout.snackbar_view, this)
        this.visibility = View.INVISIBLE

        parent_layout.background = background ?: parent_layout.background
        imageView.setImageDrawable(iconDrawable ?: imageView.drawable)
        imageView.setColorFilter(iconTint)
        textView.setTextColor(iconTint)

    }

    fun showSnackbarWithMessage(errorString: String){
        textView.text = errorString
        showSnackbar()
    }

    fun showSnackbar(){
        this.visibility = View.VISIBLE
        this.y = -this.height.toFloat()
        this.animate().y(0.0f).withLayer()
        hidden = false
        mHideHandler.removeCallbacks(mHideRunnable)
        if(AUTO_HIDE) delayedHide(AUTO_HIDE_DELAY_MILLIS)
    }

    fun hideSnackbar(){
        if(hidden) return
        hidden = true
        this.animate().y(-this.height.toFloat()).withLayer().withEndAction { this.visibility = View.INVISIBLE }

        mHideHandler.removeCallbacks(mHideRunnable)
    }

    /**
     * Schedules a call to hide() in [delayMillis], canceling any
     * previously scheduled calls.
     */
    private fun delayedHide(delayMillis: Int) {
        mHideHandler.removeCallbacks(mHideRunnable)
        mHideHandler.postDelayed(mHideRunnable, delayMillis.toLong())
    }

    companion object {
        /**
         * Whether or not the bar UI should be auto-hidden after
         * [AUTO_HIDE_DELAY_MILLIS] milliseconds.
         */
        var AUTO_HIDE = true

        /**
         * If [AUTO_HIDE] is set, the number of milliseconds to wait after
         * user interaction before hiding the bar UI.
         */
        var AUTO_HIDE_DELAY_MILLIS = 4000
    }
}