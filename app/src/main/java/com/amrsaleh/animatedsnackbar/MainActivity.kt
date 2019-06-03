package com.amrsaleh.animatedsnackbar

import android.graphics.Typeface
import android.graphics.drawable.AnimationDrawable
import android.graphics.drawable.ColorDrawable
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
        animationDrawable.setExitFadeDuration(1000)

        val mySnackBar = AnimatedSnackbar(this)
            .setBgDrawable(animationDrawable)
            .setMessage(getString(R.string.dummy_message))

        show_bar_button.setOnClickListener {
            mySnackBar.setAutoHide(true, 4000)
            mySnackBar.setAnimationDurationMillis(600)
            mySnackBar.show()
        }

        show_custom_bar_button.setOnClickListener {
            val colorDrawable = ColorDrawable(ContextCompat.getColor(this, R.color.colorAmber_A400))
            AnimatedSnackbar(this).apply {
                setIconDrawable(getDrawable(android.R.drawable.ic_dialog_email))
                setBgDrawable(colorDrawable)
                setMessage(getString(R.string.dummy_message), ContextCompat.getColor(this@MainActivity, R.color.colorRed_900))
                setTextSize(20f)
                setTypeFace(Typeface.DEFAULT_BOLD)
                show()
            }
        }


    }
}
