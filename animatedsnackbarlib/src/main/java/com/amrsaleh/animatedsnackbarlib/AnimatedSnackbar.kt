package com.amrsaleh.animatedsnackbarlib

import android.app.Activity
import android.content.Context
import android.graphics.Rect
import android.graphics.Typeface
import android.graphics.drawable.AnimationDrawable
import android.graphics.drawable.Drawable
import android.os.Handler
import android.text.SpannableString
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.LayoutRes
import kotlinx.android.synthetic.main.snackbar_view.view.*


/**
 * An animated snack bar that can be auto-hidden after
 * [autoHideDelayMillis] milliseconds.
 *
 */
class AnimatedSnackbar(context: Context, attrs : AttributeSet? = null) : FrameLayout(context, attrs) {

    private var iconDrawable : Drawable? = null
    private var iconTint : Int? = null
    private var message : SpannableString? = null
    private var textTint : Int? = null
    private var typeface : Typeface? = null
    private var textSize : Float? = null
    private var bgDrawable : Drawable? = null
    private var addStatusBarPadding = true
    /**
     * Whether or not the bar UI should be auto-hidden after
     * [autoHideDelayMillis] milliseconds.
     */
    private var autoHide = true
    /**
     * If [autoHide] is set, the number of milliseconds to wait before hiding the bar UI.
     */
    private var autoHideDelayMillis : Int = 2000
    private var animationDurationMillis : Long = 300
    private var customViewResource : Int? = null
    private var customBarView : View? = null

    private val mHideHandler = Handler()
    private val mHideRunnable = Runnable { hide() }
    private var hidden = true

    fun setIconDrawable(iconDrawable : Drawable?, iconTint : Int? = null): AnimatedSnackbar{
        val customImageView = customBarView?.findViewById<ImageView>(R.id.icon_image_view)

        if(iconDrawable != null) {
            this.iconDrawable = iconDrawable
            icon_image_view.setImageDrawable(iconDrawable)
            customImageView?.setImageDrawable(iconDrawable)
        }

        if(iconTint != null) {
            this.iconTint = iconTint
            icon_image_view.setColorFilter(iconTint)
            customImageView?.setColorFilter(iconTint)
        }
        return this
    }

    fun setMessage(message : String, textTint : Int? = null): AnimatedSnackbar{
        return setMessage(SpannableString(message), textTint)
    }

    fun setMessage(message : SpannableString, textTint : Int? = null): AnimatedSnackbar{
        this.message = message
        message_text_view.text = message

        val customTextView = customBarView?.findViewById<TextView>(R.id.message_text_view)
        customTextView?.text = message

        if(textTint != null){
            this.textTint = textTint
            message_text_view.setTextColor(textTint)
            customTextView?.setTextColor(textTint)
        }

        return this
    }

    fun setTypeFace(typeface: Typeface): AnimatedSnackbar{
        this.typeface = typeface
        message_text_view.typeface = typeface

        val customTextView = customBarView?.findViewById<TextView>(R.id.message_text_view)
        customTextView?.typeface = typeface

        return this
    }

    fun setTextSize(textSize: Float): AnimatedSnackbar{
        this.textSize = textSize
        message_text_view.textSize = textSize

        val customTextView = customBarView?.findViewById<TextView>(R.id.message_text_view)
        customTextView?.textSize = textSize

        return this
    }

    fun setBgDrawable(bgDrawable : Drawable?): AnimatedSnackbar{
        if(bgDrawable != null) {
            this.bgDrawable = bgDrawable
            parent_layout.background = bgDrawable
            customBarView?.background = bgDrawable
        }
        return this
    }

    fun setAutoHide(autoHide: Boolean, autoHideDelayMillis: Int = 2000): AnimatedSnackbar{
        this.autoHide = autoHide
        this.autoHideDelayMillis = autoHideDelayMillis
        return this
    }

    fun setAnimationDurationMillis(animationDuration : Long): AnimatedSnackbar{
        this.animationDurationMillis = animationDuration
        return this
    }

    fun setCustomView(@LayoutRes customViewResource : Int) : AnimatedSnackbar{
        this.customViewResource = customViewResource
        customBarView = inflate(context, customViewResource, null)
        parent_layout.addView(customBarView)
        default_view.visibility = View.GONE

        val customTextView = customBarView?.findViewById<TextView>(R.id.message_text_view)
        val customImageView = customBarView?.findViewById<ImageView>(R.id.icon_image_view)

        if(customTextView != null){
            message?.let{customTextView.text = it}
            textTint?.let{customTextView.setTextColor(it)}
            typeface?.let{customTextView.typeface = it}
            textSize?.let{customTextView.textSize = it}
        }

        if(customImageView != null){
            iconDrawable?.let{customImageView.setImageDrawable(it)}
            iconTint?.let{customImageView.setColorFilter(it)}
        }

        return this
    }

    fun setAddStatusBarPadding(addStatusBarPadding : Boolean) : AnimatedSnackbar{
        this.addStatusBarPadding = addStatusBarPadding
        return this
    }

    private val activity: Activity? = (context as? Activity)

    init {
        inflate(context, R.layout.snackbar_view, this)
        this.visibility = View.INVISIBLE
    }

    private fun prepareSnackbar(){
        // Attach to parent view
        addViewToParent()

        // Add status bar padding
        if( addStatusBarPadding && activity != null){
            val rectangle = Rect()
            activity.window.decorView.getWindowVisibleDisplayFrame(rectangle)
            val statusBarHeight = rectangle.top
            if(customBarView != null){
                customBarView?.setPadding(
                    customBarView?.paddingLeft ?: 0,
                    (customBarView?.paddingTop ?: 0) + statusBarHeight,
                    customBarView?.paddingRight ?: 0,
                    customBarView?.paddingBottom ?: 0)
            }else{
                parent_layout.setPadding(0,statusBarHeight,0,0)
            }
        }
    }

    private fun addViewToParent(){
        if(parent != null) return
        val decorView = activity?.window?.decorView as? ViewGroup
        decorView?.addView(this)
    }

    fun show(){
        prepareSnackbar()
        performWhenHeightIsReady {showSnackbar()}
    }

    private fun showSnackbar() : Boolean{
//        if(this.visibility == View.VISIBLE) return false
        hidden = false
        this.visibility = View.VISIBLE
        (bgDrawable as? AnimationDrawable)?.start()
        (customBarView?.background as? AnimationDrawable)?.start()
        this.y = -parent_layout.measuredHeight.toFloat()

        this.animate().y(0.0f).setDuration(animationDurationMillis).withLayer()
        mHideHandler.removeCallbacks(mHideRunnable)
        if(autoHide) delayedHide(autoHideDelayMillis)
        return true
    }

    fun hide() : Boolean{
        if(hidden) return false
        hidden = true
        this.animate().y(-parent_layout.height.toFloat()).setDuration(animationDurationMillis).withLayer().withEndAction {
            this.visibility = View.INVISIBLE
            (bgDrawable as? AnimationDrawable)?.stop()
            (parent as? ViewGroup)?.removeView(this@AnimatedSnackbar)
        }

        mHideHandler.removeCallbacks(mHideRunnable)
        return true
    }

    /**
     * Schedules a call to hide() in [delayMillis], canceling any
     * previously scheduled calls.
     */
    private fun delayedHide(delayMillis: Int) {
        mHideHandler.removeCallbacks(mHideRunnable)
        mHideHandler.postDelayed(mHideRunnable, delayMillis.toLong())
    }
}

//Extension to get height in Kotlin dynamically (waits for it to be calculated in case not ready).
fun <T : View> T.performWhenHeightIsReady(function: () -> Unit) {
    if (height == 0)
        viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                viewTreeObserver.removeOnGlobalLayoutListener(this)
                function()
            }
        })
    else function()
}