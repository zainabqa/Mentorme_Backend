package com.rohakabir.i200552

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Audiocall : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_audiocall)


    }
    fun gotobooksession(view: View) {

        val intent = Intent(this, Videocall::class.java)
        startActivity(intent)

    }
}