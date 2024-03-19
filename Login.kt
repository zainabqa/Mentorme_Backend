package com.rohakabir.i200552

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.text.SpannableString
import android.text.Spanned
import android.text.style.UnderlineSpan
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth


class Login : AppCompatActivity() {

    private lateinit var firebaseauth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val textView = findViewById<TextView>(R.id.signup)
        val text = "Sign Up"

        val spannableString = SpannableString(text)
        spannableString.setSpan(
            UnderlineSpan(),
            0,
            text.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        textView.text = spannableString


        firebaseauth = FirebaseAuth.getInstance()

        val forgotPasswordButton: Button = findViewById(R.id.forgotyourpass)
        val loginbtn = findViewById<Button>(R.id.Login_button)

        val email = findViewById<EditText>(R.id.enteremail)
        val pass = findViewById<EditText>(R.id.enterPass)




        loginbtn.setOnClickListener {

            val checkemail = email.text.toString()
            val checkpass = pass.text.toString()

            if(checkemail.isNotEmpty() && checkpass.isNotEmpty()){

                firebaseauth.signInWithEmailAndPassword(checkemail, checkpass).addOnCompleteListener {
                    if(it.isSuccessful){

                        //val intent= Intent(this, HomeScreen1::class.java)
                        //startActivity(intent)

                        val intent = Intent(this, EditProfile::class.java)
                        startActivity(intent)
                    }

                }


            }else{

                Toast.makeText(this, "Empty fields are not allowed" , Toast.LENGTH_SHORT).show()
            }



        }

        forgotPasswordButton.setOnClickListener {
            // Handle the click event and navigate to the next activity
            val intent = Intent(this, ForgotPassword::class.java)
            startActivity(intent)
        }


    }
    fun gotosignup(view: View) {
        val intent = Intent(this, Signup::class.java)
        startActivity(intent)

    }
}