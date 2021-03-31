package com.usm.autocurate.ui.auth

import android.content.Intent
import android.net.sip.SipErrorCode.TIME_OUT
import android.os.Bundle
import android.os.Handler
import android.os.PersistableBundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.usm.autocurate.R

class SplashActivity: AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splashscreen)
        window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        loadSplashScreen()


    }



    val TIME_OUT: Long = 3000

    fun loadSplashScreen(){
        Handler().postDelayed({
            // You can declare your desire activity here to open after finishing splash screen. Like MainActivity
            val intent = Intent(this, LoginActivity::class.java).apply {
                putExtra(EXTRA_MESSAGE, "hello")
            }
            startActivity(intent)
          //  finish()
        },TIME_OUT)
    }

}