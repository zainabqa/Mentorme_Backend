package com.rohakabir.i200552


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.UnderlineSpan
import android.widget.Button
import android.widget.TextView

class ResetPassword : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)


        val textView = findViewById<TextView>(R.id.logininreset)
        val text = "Login"

        val spannableString = SpannableString(text)
        spannableString.setSpan(
            UnderlineSpan(),
            0,
            text.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        textView.text = spannableString

        val resetPass_button: Button = findViewById<Button>(R.id.button_resetPass)

        resetPass_button.setOnClickListener{

            val intent = Intent(this, HomeScreen1::class.java)
            startActivity(intent)
        }

    }

}