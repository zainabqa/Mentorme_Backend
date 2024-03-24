package com.rohakabir.i200552

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Chat_UI2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_ui2)
    }
    fun navigateToNextActivity(view: View) {
        val intent = Intent(this, Profile::class.java)
        startActivity(intent)

    }
}