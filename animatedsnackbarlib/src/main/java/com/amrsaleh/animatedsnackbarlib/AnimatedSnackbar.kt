package com.amrsaleh.animatedsnackbarlib

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Handler
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.snackbar_view.view.*
import android.app.Activity
import android.util.TypedValue
import androidx.annotation.AttrRes
import androidx.core.content.res.ResourcesCompat

/**
 * An animated snack bar that can be auto-hidden after
 * [AUTO_HIDE_DELAY_MILLIS] milliseconds.
 *
 */
class AnimatedSnackbar(context: Context, attrs : AttributeSet) : LinearLayout(context, attrs) {

    private var iconDrawable : Drawable? = context.getDrawable(android.R.drawable.ic_dialog_info)
    private var iconTint = Color.WHITE
    private var textTint = Color.WHITE
    private var bgColor = ResourcesCompat.getColor(resources, R.color.error_red, null)
    private var statusBarMatch = false

    private val mHideHandler = Handler()
    private val mHideRunnable = Runnable { hideSnackbar() }
    private var hidden = true

    private var statusOrigColor: Int = context.themeColor(R.attr.colorPrimaryDark)
    private var activity: Activity? = null

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
                    bgColor = getColor(R.styleable.AnimatedSnackbar_bg_color, bgColor)
                    statusBarMatch = getBoolean(R.styleable.AnimatedSnackbar_status_bar_match, false)
                } finally {
                    recycle()
            }
        }
        inflate(context, R.layout.snackbar_view, this)
        this.visibility = View.INVISIBLE

//        parent_layout.background = background ?: parent_layout.background
        parent_layout.setBackgroundColor(bgColor)
        imageView.setImageDrawable(iconDrawable ?: imageView.drawable)
        imageView.setColorFilter(iconTint)
        textView.setTextColor(textTint)

        if(statusBarMatch){
            activity = (context as? Activity)
            activity?.apply {

//                // clear FLAG_TRANSLUCENT_STATUS flag:
//                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
//
//                // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
//                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
//
//                // finally change the color
//                window.statusBarColor = bgColor
            }
        }

    }

    fun showSnackbarWithMessage(errorString: String){
        textView.text = errorString
        showSnackbar()
    }

    fun showSnackbar(){
        if(this.visibility == View.VISIBLE) return
        hidden = false
        this.visibility = View.VISIBLE
        this.y = -this.height.toFloat()
        if(statusBarMatch) {
            statusOrigColor = activity?.window?.statusBarColor ?: statusOrigColor
            activity?.window?.statusBarColor = bgColor
        }
        this.animate().y(0.0f).withLayer()
        mHideHandler.removeCallbacks(mHideRunnable)
        if(AUTO_HIDE) delayedHide(AUTO_HIDE_DELAY_MILLIS)
    }

    fun hideSnackbar(){
        if(hidden) return
        hidden = true
        this.animate().y(-this.height.toFloat()).withLayer().withEndAction {
            this.visibility = View.INVISIBLE
            // finally change the color of status bar back
            activity?.window?.statusBarColor = statusOrigColor
        }

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

fun Context.themeColor(@AttrRes attrRes: Int): Int {
    val typedValue = TypedValue()
    theme.resolveAttribute (attrRes, typedValue, true)
    return typedValue.data
}