package com.amrsaleh.animatedsnackbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        show_bar_button.setOnClickListener {
            snackbar.showSnackbarWithMessage(getString(R.string.dummy_message))
        }

        show_custom_bar_button.setOnClickListener {
            custom_snackbar.showSnackbarWithMessage(getString(R.string.dummy_message))
        }

//        window.statusBarColor = Color.DKGRAY
    }
}
