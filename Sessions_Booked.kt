package com.rohakabir.i200552

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Sessions_Booked : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sessions_booked)

    }
    fun navigateToNextActivity(view: View) {
        val intent = Intent(this, Notifications::class.java)
        startActivity(intent)

    }
}