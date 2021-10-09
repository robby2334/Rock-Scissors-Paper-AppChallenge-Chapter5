package com.robbie.rock_scissors_paper_appchallenge_chapter5.utils

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import com.robbie.rock_scissors_paper_appchallenge_chapter5.R
import com.robbie.rock_scissors_paper_appchallenge_chapter5.ui.instruction.PageIntro

@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity() {
    private var timer: CountDownTimer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        supportActionBar?.hide()
        splashScreen()


    }

    private fun splashScreen() {
        timer = object : CountDownTimer(3000, 1000) {
            override fun onTick(millUntilFinished: Long) {
            }

            override fun onFinish() {
                val intent = Intent(this@SplashScreen, PageIntro::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)

            }
        }
        timer?.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        timer.let {
            it?.cancel()
            timer = null
        }
    }
}