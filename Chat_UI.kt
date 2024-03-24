package com.rohakabir.i200552

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Chat_UI : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_ui)
    }
    fun navigateToNextActivity(view: View) {
        val intent = Intent(this, Chat_UI2::class.java)
        startActivity(intent)

    }
}