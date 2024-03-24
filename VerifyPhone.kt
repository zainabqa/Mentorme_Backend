package com.rohakabir.i200552

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.TextView
import android.R.layout
import android.content.Intent
import android.view.View

class VerifyPhone : AppCompatActivity() {

    private lateinit var sendCodeAgainTextView: TextView
    private var countdownTimer: CountDownTimer? = null
    private val countdownDurationMillis = 60000 // 60 seconds

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verify_phone)

        sendCodeAgainTextView = findViewById(R.id.sendCodeAgainTextView)
        sendCodeAgainTextView.setOnClickListener {
            startCountdown()
        }
    }

    private fun startCountdown() {
        // Cancel the previous timer if it exists
        countdownTimer?.cancel()

        // Start the countdown timer
        countdownTimer = object : CountDownTimer(countdownDurationMillis.toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val secondsRemaining = millisUntilFinished / 1000
                sendCodeAgainTextView.text = getString(R.string.send_code_again_timer, secondsRemaining)
            }

            override fun onFinish() {
                sendCodeAgainTextView.text = getString(R.string.send_code_again)
            }
        }.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        // Cancel the countdown timer to prevent memory leaks
        countdownTimer?.cancel()
    }
    fun onArrowClick(view: View) {
        // Handle the click event here
        // For example, navigate to another screen
        val intent = Intent(this, Signup::class.java)
        startActivity(intent)
    }
}