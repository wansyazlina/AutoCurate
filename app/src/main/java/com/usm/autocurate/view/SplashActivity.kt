package com.usm.autocurate.view

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.view.WindowInsets
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.usm.autocurate.R
import com.usm.autocurate.databinding.ActivityHomeBinding

class SplashActivity: AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splashscreen)

        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }

        loadSplashScreen()

    }



    val TIME_OUT: Long = 3000

    fun loadSplashScreen(){
        Handler(Looper.getMainLooper()).postDelayed({
            // You can declare your desire activity here to open after finishing splash screen. Like MainActivity
            val intent = Intent(this, LoginRegisterActivity::class.java).apply {
                putExtra(EXTRA_MESSAGE, "hello")
            }
            startActivity(intent)
            finish()
        },TIME_OUT)
    }

}