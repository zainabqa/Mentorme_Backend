package com.rohakabir.i200552

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.rohakabir.i200552.databinding.ActivitySignupBinding

class Signup : AppCompatActivity() {

    private lateinit var firebaseauth : FirebaseAuth
    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater) // Inflate the layout using view binding
        setContentView(binding.root)


        firebaseauth = FirebaseAuth.getInstance()

        val btn=findViewById<Button>(R.id.signupbtn)
        val findname = findViewById<EditText>(R.id.entname)
        val findemail = findViewById<EditText>(R.id.enterEmail)
        val findcontact = findViewById<EditText>(R.id.enternumber)
        val findcountry = findViewById<EditText>(R.id.enterCountry)
        val findcity = findViewById<EditText>(R.id.enterCity)
        val findpass = findViewById<EditText>(R.id.enterPwd)

        btn.setOnClickListener {

            val name = findname.text.toString()
            val email = findemail.text.toString()
            val contact = findcontact.text.toString()
            val country = findcountry.text.toString()
            val city = findcity.text.toString()
            val password = findpass.text.toString()

            if(name.isNotEmpty() && email.isNotEmpty() && contact.isNotEmpty() && country.isNotEmpty()

                && city.isNotEmpty() && password.isNotEmpty()){

                firebaseauth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                    if(it.isSuccessful){
                        val userId = FirebaseAuth.getInstance().currentUser?.uid

                        if (userId != null) {
                            val userReference = FirebaseDatabase.getInstance().getReference("users").child(userId)

                            val userData = Userdata(
                                username = binding.entname.text.toString(),
                                useremail = binding.enterEmail.text.toString(),
                                contactnumber = binding.enternumber.text.toString(),
                                country = binding.enterCountry.text.toString(),
                                city = binding.enterCity.text.toString(),
                                password = binding.enterPwd.text.toString()
                            )

                            userReference.setValue(userData).addOnCompleteListener {


                                    Toast.makeText(this, "User information saves" , Toast.LENGTH_SHORT).show()



                            }


                            }
                        val intent= Intent(this, Login::class.java)
                        startActivity(intent)

                    }
                    else{
                        Toast.makeText(this, it.exception.toString() , Toast.LENGTH_SHORT).show()
                    }

                }


            }else{

                Toast.makeText(this, "Empty fields are not allowed" , Toast.LENGTH_SHORT).show()
            }




        }


    }

    fun navigateToNextActivity(view: View) {
        val intent = Intent(this, HomeScreen1::class.java)
        startActivity(intent)

    }
}