package com.amrsaleh.animatedsnackbar

import android.graphics.Typeface
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.amrsaleh.animatedsnackbarlib.AnimatedSnackbar
import kotlinx.android.synthetic.main.activity_main.*





class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // initializing animation drawable by getting background from constraint layout
        val animationDrawable = getDrawable(R.drawable.drawable_gradient_animation_list) as AnimationDrawable

        // setting enter fade animation duration
        animationDrawable.setEnterFadeDuration(0)
        // setting exit fade animation duration
        animationDrawable.setExitFadeDuration(800)

        val mySnackBar = AnimatedSnackbar(this)

        show_bar_button.setOnClickListener {
            mySnackBar.setMessage(getString(R.string.dummy_message)).show()
        }

        show_custom_bar_button.setOnClickListener {
//            val colorDrawable = ColorDrawable(ContextCompat.getColor(this, R.color.darkGray))
            AnimatedSnackbar(this).apply {
                setIconDrawable(getDrawable(android.R.drawable.ic_dialog_email), ContextCompat.getColor(this@MainActivity, R.color.greenLight))
                setBgDrawable(animationDrawable)
//                setBgDrawable(colorDrawable)
                setMessage(getString(R.string.dummy_message), ContextCompat.getColor(this@MainActivity, android.R.color.white))
                setTextSize(15f)
                setAutoHide(true, 4000)
                setAnimationDurationMillis(600)
                setTypeFace(Typeface.DEFAULT_BOLD)
                show()
            }
        }

        show_custom_view_btn.setOnClickListener {
            AnimatedSnackbar(this)
                .setCustomView(R.layout.my_custom_view)
                .setAddStatusBarPadding(true) // default true
                .setAutoHide(true, 4000) // deafult true, 2000
                .show()
        }

    }
}
